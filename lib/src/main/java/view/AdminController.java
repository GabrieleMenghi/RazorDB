package view;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Client;

public class AdminController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	Button customers;
	@FXML
	TableView clientsTable;
	
	private final ObservableList<Client> data =
	        FXCollections.observableArrayList(cTable.findAll());
	
	@FXML
	public void viewCustomers() {
		clientsTable.setItems(data);
	}
}
