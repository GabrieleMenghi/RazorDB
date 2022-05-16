package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.Client;
import utils.Utils;

public class ClientsTable {
	
	public static final String TABLE_NAME = "clienti";
	private final Connection connection; 
    

    public ClientsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    private List<Client> readClientsFromResultSet(final ResultSet resultSet) {
    	final List<Client> studentsList = new ArrayList<>();
        try {
			while(resultSet.next()) {
				 studentsList.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), Optional.of(resultSet.getString(4)), 
						 Optional.of(resultSet.getString(5)), Optional.of(resultSet.getString(6)), Optional.of(resultSet.getLong(7))));
			}
        } catch (SQLException e) {}
        return studentsList;
    }
    
    public List<Client> findAll() {
    	final List<Client> studentsList = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		studentsList.addAll(0, readClientsFromResultSet(rs));
    	} catch (final SQLException e) {}
    	return studentsList;
    }
}
