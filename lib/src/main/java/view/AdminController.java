package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Client;

public class AdminController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
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
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void viewCustomers() {
		final ObservableList<Client> data =
		        FXCollections.observableArrayList();
		cTable.findAll().forEach(c -> {
			data.add(c);
		});
		clientsTable.setItems(data);
	}

	@FXML
	public void createClient() throws IOException {
		root = FXMLLoader.load(getClass().getResource("newClient.fxml"));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void deleteClient() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		int id = clientsTable.getSelectionModel().getSelectedItem().getId();
		alert.setTitle("Conferma");
		String s = "Sei sicuro di voler eliminare l'utente " + id + "?";
		alert.setContentText(s);
		 
		Optional<ButtonType> result = alert.showAndWait();
		 
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			clientsTable.getItems().remove(clientsTable.getSelectionModel().getSelectedIndex());
			
			cTable.delete(id);
		} else if ((result.isPresent()) && (result.get() == ButtonType.CANCEL)) {
			alert.close();
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewCustomers();
		codcliente.setCellValueFactory(new PropertyValueFactory<>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		cognome.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		via.setCellValueFactory(new PropertyValueFactory<>("address"));
		città.setCellValueFactory(new PropertyValueFactory<>("city"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
	}
}
