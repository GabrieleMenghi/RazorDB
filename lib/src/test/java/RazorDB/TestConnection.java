package RazorDB;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import db.ConnectionProvider;
import db.tables.AppointmentsTable;
import db.tables.ClientAppointmentsTable;
import db.tables.ClientsTable;
import model.Appointment;
import model.Client;
import model.ClientAppointment;

public class TestConnection {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
    private long n1 = 3912258161L;
    Client c1 = new Client(1, "Gabriele", "Menghi", null, "Rimini", 
			"gabry.menghi@gmail.com", n1);
    long n2 = 3928458171L;
	Client c2 = new Client(2, "Marco", "Verdi", null, "Cesena", 
			"marcoverdi@gmail.com", n2);
	long n3 = 3362558472L;
	Client c3 = new Client(4, "Giacomo", "Bianchi", "Via Arancioni 31", "Bologna", 
			"giacomobianchi@gmail.com", n3);
	/*Client c4 = new Client("Raffele", "Marroni", Optional.of("Via"), Optional.of("Citta"), 
			Optional.of("Mail2"), Optional.of(null));*/
	/*@Test
	public void testConnection() {
		assertIterableEquals(List.of(c1, c2, c3), cTable.findAll());
	}*/
	
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
	
	final static AppointmentsTable aTable = new AppointmentsTable(connectionProvider.getMySQLConnection());
	
	Appointment a1 = new Appointment(1, Date.valueOf("2022-05-27"), Time.valueOf("17:45:00"), 1, null, 1, null, null);
	
	/*@Test
	public void testNewAppointment() {
		assertTrue(aTable.save(a1));
	}*/
	
	final static ClientAppointmentsTable caTable = new ClientAppointmentsTable(connectionProvider.getMySQLConnection());
	
	ClientAppointment ca1 = new ClientAppointment(Date.valueOf("2022-05-19"), Time.valueOf("09:30:00"), "Lorenzo", 0, null);
	ClientAppointment ca2 = new ClientAppointment(Date.valueOf("2022-05-27"), Time.valueOf("17:45:00"), "Lorenzo", 0, null);
	ClientAppointment ca3 = new ClientAppointment(Date.valueOf("2022-05-31"), Time.valueOf("11:45:00"), "Lorenzo", 0, null);
	@Test
	public void testFindApp() {
		assertEquals(List.of(ca1,ca2,ca3), caTable.findAll(1));
	}
	
	Client ctest = new Client(1, "Gabri", "Menghi", null, "Rimini", "gabry.menghi@gmail.com", 3912258161L);
	@Test
	public void testUpdate() {
		//assertTrue(cTable.updateClient(ctest));
		System.out.println(cTable.findClientById(1));
	}
}
