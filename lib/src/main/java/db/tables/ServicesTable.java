package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Service;

public class ServicesTable {
	public static final String TABLE_NAME = "servizi";
	private final Connection connection; 
    

    public ServicesTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    private List<Service> readClientsFromResultSet(final ResultSet resultSet) {
    	final List<Service> services = new ArrayList<>();
        try {
			while(resultSet.next()) {
				 services.add(new Service(resultSet.getString(1), resultSet.getString(2), resultSet.getFloat(3), 
						 					resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), 
						 					resultSet.getInt(7), resultSet.getInt(8)));
			}
        } catch (SQLException e) {}
        return services;
    }
    
    public List<Service> findAll() {
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		return readClientsFromResultSet(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
}
