package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Barber;

public class BarbersTable {
	public static final String TABLE_NAME = "barbieri";
	private final Connection connection; 
    

    public BarbersTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Barber> findAll() {
    	final List<Barber> barbers = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		while(rs.next()) {
				 barbers.add(new Barber(rs.getInt(1), 
						 				rs.getString(2), 
						 				rs.getString(3), 
						 				rs.getString(4), 
						 				rs.getDate(5),
						 				rs.getString(6), 
						 				rs.getLong(7)));
			}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return barbers;
    }
}
