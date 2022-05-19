package db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Receipt;

public class ReceiptsTable {
	public static final String TABLE_NAME = "scontrini";
	private final Connection connection; 
    

    public ReceiptsTable(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }
    
    public List<Receipt> findAll() {
    	final List<Receipt> receipts = new ArrayList<>();
    	try(final Statement statement = this.connection.createStatement()){
    		final ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME
    													+ " s, barbieri b, clienti c"
    													+ " WHERE s.CodBarbiere = b.CodBarbiere"
    													+ " AND s.CodCliente = c.CodCliente");    
    		
    		while(rs.next()) {
				 receipts.add(new Receipt(rs.getInt(1), 
						 					rs.getDate(2), 
						 					rs.getTime(3), 
						 					rs.getFloat(4), 
						 					rs.getInt(5), 
						 					rs.getInt(6)));
			}
    	} catch (final SQLException e) {
    		throw new IllegalStateException();
    	}
    	return receipts;
    }
}
