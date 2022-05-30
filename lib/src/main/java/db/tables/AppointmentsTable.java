package db.tables;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Appointment;

public class AppointmentsTable {
	public static final String TABLE_NAME = "appuntamenti";
	private final Connection connection; 
    

    public AppointmentsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    private List<Appointment> readAppointmentsFromResultSet(final ResultSet resultSet) {
    	final List<Appointment> appointments = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);  
    		while(rs.next()) {
				 appointments.add(new Appointment(rs.getInt(1), 
						 							rs.getDate(2), 
						 							rs.getTime(3), 
						 							rs.getInt(4), 
						 							rs.getInt(5), 
						 							rs.getInt(6), 
						 							rs.getInt(7), 
						 							rs.getDate(8)));
			}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return appointments;
    }
    
    public List<Appointment> findAll() {
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		return readAppointmentsFromResultSet(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
    
    public boolean save(final Appointment appointment, final List<String> services) {
        final String query = "INSERT INTO " + TABLE_NAME
        						+ " VALUES (?,?,?,?,?,?,?,?)";
        final String query2 = "{call newAssociation (?,?,?,?)}";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getIdPerformingBarber());
            statement.setDate(2, appointment.getDate());
            statement.setTime(3, appointment.getTime());
            statement.setInt(4, appointment.getIdBookingClient());
            statement.setObject(5, appointment.getIdPerformingClient(), Types.INTEGER);
            statement.setInt(6, appointment.getIdBookingBarber());
            statement.setObject(7, appointment.getReceiptNumber(), Types.INTEGER);
            if(appointment.getReceiptDate() == null) {
            	statement.setNull(8, Types.DATE);
            } else {
            	statement.setDate(8, appointment.getReceiptDate());            	
            }
            statement.executeUpdate();
            final CallableStatement statement2 = this.connection.prepareCall(query2);
            for(String s : services) {
            	statement2.setString(1, s);
            	statement2.setInt(2, appointment.getIdPerformingBarber());
            	statement2.setDate(3, appointment.getDate());
            	statement2.setTime(4, appointment.getTime());
            	statement2.addBatch();
            }
            statement2.executeBatch();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        
    }
    
    public boolean updateAppointment(final Integer barber, final Date oldDate, final Time oldTime, final Date newDate, final Time newTime) {
    	String query = "{call updateAppointment (?,?,?,?,?)}";
    	try(CallableStatement cs = this.connection.prepareCall(query)){
    		cs.setInt(1, barber);
    		cs.setDate(2, oldDate);
    		cs.setTime(3, oldTime);
    		cs.setDate(4, newDate);
    		cs.setTime(5, newTime);
    		return cs.executeUpdate() > 0;
    	} catch (SQLException e) {
    		return false;
    	}		
    }
    
    public List<String> getAppointmentServices(final Integer barber, final Date date, final Time time) {
    	List<String> services = new ArrayList<>();
    	String query = "SELECT s.Descrizione"
    					+ " FROM appuntamenti app, associazioni ass, servizi s"
    					+ " WHERE app.BarbiereEffettuante = ass.BarbiereEffettuante"
    					+ " AND app.Data = ass.DataAppuntamento"
    					+ " AND app.Ora = ass.OraAppuntamento"
    					+ " AND ass.IdServizio = s.Denominazione"
    					+ " AND app.BarbiereEffettuante = ?"
    					+ " AND app.Data = ?"
    					+ " AND app.Ora = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setInt(1, barber);
    		statement.setDate(2, date);
    		statement.setTime(3, time);
    		final ResultSet rs = statement.executeQuery();  
    		while(rs.next()) {
				 services.add(rs.getString(1));
			}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return services;
    }
}
