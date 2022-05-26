package db.tables;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.DetailedAppointment;

public class DetailedAppointmentsTable {
	private final Connection connection; 
    

    public DetailedAppointmentsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<DetailedAppointment> readAppFromResultset(final ResultSet rs) {
    	final List<DetailedAppointment> appointments = new ArrayList<>();
    	try {											
    		while(rs.next()) {
				 appointments.add(new DetailedAppointment(rs.getDate(1), 
						 									rs.getTime(2),
						 									rs.getInt(3),
						 									rs.getInt(4), 
						 									rs.getString(5), 
						 									rs.getString(6)));
    		}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return appointments;
    }
    
    public List<DetailedAppointment> findAll() {
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT a.Data, a.Ora, a.BarbiereEffettuante, a.ClientePrenotante, c.Nome, c.Cognome FROM"
    													+ " appuntamenti a, clienti c"
    													+ " WHERE a.ClientePrenotante = c.CodCliente");  
    		return readAppFromResultset(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
    
    public List<DetailedAppointment> findAppointmentsByDate(final Date date)	{
    	String query = "SELECT a.Data, a.Ora, a.BarbiereEffettuante, a.ClientePrenotante, c.Nome, c.Cognome FROM"
						+ " appuntamenti a, clienti c"
						+ " WHERE a.ClientePrenotante = c.CodCliente"
						+ " AND a.Data = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setDate(1, date);
    		final ResultSet rs = statement.executeQuery();
    		return readAppFromResultset(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
}
