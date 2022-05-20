package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.ClientAppointment;

public class ClientAppointmentsTable {
	
	private final Connection connection; 
    
    public ClientAppointmentsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<ClientAppointment> readAppFromResultset(final ResultSet rs) {
    	final List<ClientAppointment> appointments = new ArrayList<>();
    	try {											
    		while(rs.next()) {
				 appointments.add(new ClientAppointment(rs.getDate(1), 
						 									rs.getTime(2), 
						 									rs.getString(3), 
						 									rs.getInt(4), 
						 									rs.getDate(5)));
    		}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return appointments;
    }
    
    public List<ClientAppointment> findAll(final Integer clientId) {
    	String query = "SELECT a.Data, a.Ora, b.Nome, a.NumScontrino, a.DataScontrino FROM"
						+ " appuntamenti a, barbieri b"
						+ " WHERE a.BarbierePrenotante = b.CodBarbiere"
						+ " AND a.ClientePrenotante = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setInt(1, clientId);
    		final ResultSet rs = statement.executeQuery();  
    		return readAppFromResultset(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
}
