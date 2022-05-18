package RazorDB;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import model.Client;

public class TestConnection {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
    private long n1 = 3912258161L;
    Client c1 = new Client(1, "Gabriele", "Menghi", Optional.empty(), Optional.of("Rimini"), 
			Optional.of("gabry.menghi@gmail.com"), Optional.of(n1));
    long n2 = 3928458171L;
	Client c2 = new Client(2, "Marco", "Verdi", Optional.empty(), Optional.of("Cesena"), 
			Optional.of("marcoverdi@gmail.com"), Optional.of(n2));
	long n3 = 3362558472L;
	Client c3 = new Client(4, "Giacomo", "Bianchi", Optional.of("Via Arancioni 31"), Optional.of("Bologna"), 
			Optional.of("giacomobianchi@gmail.com"), Optional.of(n3));
	/*Client c4 = new Client("Raffele", "Marroni", Optional.of("Via"), Optional.of("Citta"), 
			Optional.of("Mail2"), Optional.of(null));*/
	@Test
	public void testConnection() {
		assertIterableEquals(List.of(c1, c2, c3), cTable.findAll());
	}
	
	/*@Test
	public void testSave() {
		assertFalse(cTable.save(c2));
		assertFalse(cTable.save(c3));
	}*/
	
	/*@Test
	public void testPresence() {
		assertTrue(cTable.isClientPresent(1));
		assertFalse(cTable.isClientPresent(3));
	}*/
	
	/*@Test
	public void testNoPhone() {
		int id = cTable.save(c4);
		System.out.println(id);
		//cTable.delete(22);
	}*/
}
