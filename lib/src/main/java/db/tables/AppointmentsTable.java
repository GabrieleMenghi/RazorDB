package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import model.Appointment;

public class AppointmentsTable {
	public static final String TABLE_NAME = "appuntamenti";
	private final Connection connection; 
    

    public AppointmentsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Appointment> findAll() {
    	final List<Appointment> appointments = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);  
    		while(rs.next()) {
				 appointments.add(new Appointment(rs.getInt(1), 
						 							rs.getDate(2), 
						 							rs.getTime(3), 
						 							rs.getInt(4), 
						 							Optional.ofNullable(rs.getInt(5)), 
						 							rs.getInt(6), 
						 							Optional.ofNullable(rs.getInt(7)), 
						 							Optional.ofNullable(rs.getDate(8))));
			}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return appointments;
    }
}
