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

import model.Appointment;
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
    
    public Client findClientById(final Integer id) {
    	Client c = null;
    	String query = "SELECT * FROM " + TABLE_NAME
						+ " WHERE CodCliente = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setInt(1, id);
    		final ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			c = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
    							rs.getString(5), rs.getString(6), rs.getLong(7));
    		}
    		return c;
    	} catch (final Exception e) {
    		return null;
    	}
    }
    
    public boolean addFidelityById(final Integer id, final Integer balance) {
    	final String query = "INSERT INTO fidelity (SaldoPunti, CodCliente)"
    							+ " VALUES (?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, balance);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean addAppointmentById(final Integer id, final Appointment appointment) {
    	final String query = "INSERT INTO appuntamenti"
    							+ " (BarbiereEffettuante, Data, Ora, ClientePrenotante, ClienteEffettuante, BarbierePrenotante, NumScontrino, DataScontrino)"
    							+ " VALUES (?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getIdPerformingBarber());
            statement.setDate(2, appointment.getDate());
            statement.setTime(3, appointment.getTime());
            statement.setInt(4, id);
            statement.setObject(5, appointment.getIdPerformingClient(), Types.INTEGER);
            statement.setInt(6, appointment.getIdBookingBarber());
            statement.setObject(7, appointment.getReceiptNumber(), Types.INTEGER);
            statement.setObject(8, appointment.getReceiptDate(), Types.DATE);
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public List<Client> viewClientsOverAverage(final Double average){
    	String query = "SELECT c.* FROM " + TABLE_NAME
    					+ " c JOIN scontrini s ON (c.CodCliente = s.CodCliente)"
    					+ " GROUP BY c.CodCliente"
    					+ " HAVING ? <= avg(ImportoTotale)";
    	try(PreparedStatement st = this.connection.prepareStatement(query)){
    		st.setDouble(1, average);
    		ResultSet rs = st.executeQuery();
    		return readClientsFromResultSet(rs);
    	} catch (SQLException e) {
    		throw new IllegalStateException(e);
    	}
    }
    
    public List<Client> viewTop3PremiumClients(){
    	String query = "SELECT c.* FROM " + TABLE_NAME
    					+ " c, appuntamenti app, associazioni ass"
    					+ " WHERE c.CodCliente = app.ClientePrenotante"
    					+ " AND app.BarbiereEffettuante = ass.BarbiereEffettuante"
    					+ " AND app.Data = ass.DataAppuntamento"
    					+ " AND app.Ora = ass.OraAppuntamento"
    					+ " AND (ass.IdServizio = 'TP' or ass.IdServizio = 'BP')"
    					+ " GROUP BY c.CodCliente, c.Nome, c.Cognome, c.Via, c.Città, c.Mail, c.NumTelefono"
    					+ " ORDER BY count(*) desc"
    					+ " LIMIT 3";
    	try(Statement st = this.connection.createStatement()){
    		ResultSet rs = st.executeQuery(query);
    		return readClientsFromResultSet(rs);
    	} catch (SQLException e) {
    		throw new IllegalStateException(e);
    	}
    }
}
