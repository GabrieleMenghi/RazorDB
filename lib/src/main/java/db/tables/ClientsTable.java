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
        /*final List<Student> students = new ArrayList<>();
        try {
            // ResultSet encapsulate a pointer to a table with the results: it starts with the pointer
            // before the first row. With next the pointer advances to the following row and returns 
            // true if it has not advanced past the last row
            while (resultSet.next()) {
                // To get the values of the columns of the row currently pointed we use the get methods 
                final int studentId = resultSet.getInt("id");
                final String firstName = resultSet.getString("firstName");
                final String lastName = resultSet.getString("lastName");
                final Optional<Date> birthday = Optional.ofNullable(Utils.sqlDateToDate(resultSet.getDate("birthday")));
                // After retrieving all the data we create a Student object
                final Student student = new Student(studentId, firstName, lastName, birthday);
                students.add(student);
            }
        } catch (final SQLException e) {}
        return students;
         * */
    }
    
    public List<Client> findAll() {
    	final List<Client> studentsList = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
    		return readClientsFromResultSet(rs);
    	} catch (final SQLException e) {}
    	return studentsList;
    }
    
    public Client find1() {
    	Client c1 = null;
    	String query = "SELECT * FROM " + TABLE_NAME
				+ " WHERE id = ?";
    	try(final PreparedStatement statement = this.connection.prepareStatement(query)){
    		statement.setInt(1, 4);
    		final ResultSet rs = statement.executeQuery();
    		int id = rs.getInt("CodCliente");
    		String fn = rs.getString("Nome");
    		String ln = rs.getString("Cognome");
    		Optional<String> address = Optional.of(rs.getString("Via"));
    		Optional<String> city = Optional.of(rs.getString("Città"));
    		Optional<String> mail = Optional.of(rs.getString("Mail"));
    		Optional<Long> phone = Optional.of(rs.getLong("NumTelefono"));
    		c1 = new Client(id, fn, ln, address, city, mail, phone);
    	} catch (final SQLException e) {}
    	return c1;
    }
    
    public boolean save(final Client client) {
        final String query = "INSERT INTO " + TABLE_NAME + "(Nome, Cognome, Via, Città, Mail, NumTelefono) "
        		+ "VALUES (?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getAddress().orElse(null));
            statement.setString(4, client.getCity().orElse(null));
            statement.setString(5, client.getMail().orElse(null));
            statement.setLong(6, client.getPhone().orElse(null));
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
