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
    	final List<Client> clients = new ArrayList<>();
        try {
			while(resultSet.next()) {
				 clients.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
						 Optional.ofNullable(resultSet.getString(4)), Optional.ofNullable(resultSet.getString(5)), 
						 Optional.ofNullable(resultSet.getString(6)), Optional.ofNullable(resultSet.getLong(7))));
			}
        } catch (SQLException e) {}
        return clients;
    }
    
    public List<Client> findAll() {
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		return readClientsFromResultSet(rs);
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    }
    
    public boolean save(final Client client) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome, Cognome, Via, Città, Mail, NumTelefono) "
        		+ "VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getCity());
            statement.setString(5, client.getMail());
            statement.setLong(6, client.getPhone());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean isClientPresent(final int id) {
    	final String query = "SELECT * FROM " + TABLE_NAME
    							+ " WHERE CodCliente = ?";
    	try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if(rs.isBeforeFirst()) {
            	return true;            	
            }
            return false;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            return false;
        }
    }
}
