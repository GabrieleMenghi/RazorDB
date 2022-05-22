package db.tables;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Client;
import model.Fidelity;

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
						 resultSet.getString(4), resultSet.getString(5), 
						 resultSet.getString(6), resultSet.getLong(7)));
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
    
    public int save(final Client client) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome, Cognome, Via, Città, Mail, NumTelefono) "
        		+ "VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setObject(3, client.getAddress(), Types.VARCHAR);
            statement.setObject(4, client.getCity(), Types.VARCHAR);
            statement.setObject(5, client.getMail(), Types.VARCHAR);
            statement.setObject(6, client.getPhone(), Types.BIGINT);
            statement.executeUpdate();
            try(ResultSet key = statement.getGeneratedKeys()){
            	if(key.next()) {
            		int id = key.getInt(1);
            		client.setId(id);
            		return id;
            	} else{
            		return -1;
            	}
            }            
        } catch (final SQLIntegrityConstraintViolationException e) {
            return -2;
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
    }
    
    public boolean delete(int id) {
        final String query = "DELETE FROM " + TABLE_NAME + 
        		" WHERE CodCliente = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
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
    
    public Fidelity findFidelityById(final Integer id) {
    	Fidelity f = null;
    	String query = "SELECT * FROM"
				+ " fidelity f"
				+ " WHERE f.CodCliente = ?"
				+ " ORDER BY f.CodFidelity DESC"
				+ " LIMIT 1";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setInt(1, id);
    		final ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			f = new Fidelity(rs.getInt(1), rs.getInt(2), rs.getInt(3));
    		}
    		return f;
    	} catch (final Exception e) {
    		return null;
    	}
    }
    
    public boolean updateClient(final Client client) {
    	String query = "{call updateClient (?,?,?,?,?,?,?)}";
    	try(CallableStatement cs = this.connection.prepareCall(query)){
    		cs.setString(1, client.getFirstName());
    		cs.setString(2, client.getLastName());
    		cs.setObject(3, client.getAddress(), Types.VARCHAR);
    		cs.setObject(4, client.getCity(), Types.VARCHAR);
    		cs.setObject(5, client.getMail(), Types.VARCHAR);
    		cs.setObject(6, client.getPhone(), Types.BIGINT);
    		cs.setInt(7, client.getId());
    		return cs.executeUpdate() > 0;
    	} catch (SQLException e) {
    		return false;
    	}
    			
    }
    
    public boolean updateFidelity(final Fidelity fidelity) {
    	String query = "{call updateFidelity (?,?)}";
    	try(CallableStatement cs = this.connection.prepareCall(query)){
    		cs.setInt(1, fidelity.getBalance());
	    	cs.setInt(2, fidelity.getClientId());
    		return cs.executeUpdate() > 0;
    	} catch (SQLException e) {
    		return false;
    	}
    }
    
    public List<Client> viewClientsByName(final String name) throws SQLException{
    	String query = "SELECT * FROM " + TABLE_NAME
    					+ " WHERE Nome LIKE ?";
    	try(PreparedStatement st = this.connection.prepareStatement(query)){
    		st.setString(1, "%" + name + "%");
    		ResultSet rs = st.executeQuery();
    		return readClientsFromResultSet(rs);
    	} catch (SQLException e) {
    		throw e;
    	}
    }
}
