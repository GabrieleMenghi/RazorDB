package db.tables;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
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
    
    public List<Appointment> findAppointmentsByDate(final Date date) {
    	String query = "SELECT * FROM " + TABLE_NAME
						+ " WHERE Data = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setDate(1, date);
    		final ResultSet rs = statement.executeQuery();
    		return readAppointmentsFromResultSet(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
    
    public boolean save(final Appointment appointment) {
        final String query = "INSERT INTO " + TABLE_NAME
        						+ " VALUES (?,?,?,?,?,?,?,?)";
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
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
