package view;

import java.net.URL;
import java.util.ResourceBundle;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

public class AdminController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	Button customers;
	@FXML
	TableView<Client> clientsTable;
	
	@FXML
	TableColumn<Client, String> codcliente;
	@FXML
	TableColumn<Client, String> nome;
	@FXML
	TableColumn<Client, String> cognome;
	@FXML
	TableColumn<Client, String> via;
	@FXML
	TableColumn<Client, String> città;
	@FXML
	TableColumn<Client, String> mail;
	@FXML
	TableColumn<Client, String> telefono;
	
	@FXML
	public void viewCustomers() {
		final ObservableList<Client> data =
		        FXCollections.observableArrayList();
		cTable.findAll().forEach(c -> {
			data.add(c);
		});
		clientsTable.setItems(data);
		System.out.println(data);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codcliente.setCellValueFactory(new PropertyValueFactory<>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		cognome.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		via.setCellValueFactory(new PropertyValueFactory<>("address"));
		città.setCellValueFactory(new PropertyValueFactory<>("city"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
	}
}
