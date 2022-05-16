package db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

public class ClientsTable {
	private final Connection connection; 
    

    public ClientsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
}
