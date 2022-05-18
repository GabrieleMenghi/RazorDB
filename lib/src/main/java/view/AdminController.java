package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import db.ConnectionProvider;
import db.tables.AppointmentsTable;
import db.tables.BarbersTable;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Appointment;
import model.Barber;
import model.Client;

public class AdminController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    
    /* ********** *
     *   CLIENTI  *
     * ********** */
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
		Stage stage;
		Scene scene;
		Parent root;
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
	
	/* *************** *
     *   APPUNTAMENTI  *
     * *************** */
final static AppointmentsTable aTable = new AppointmentsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TableView<Appointment> appointmentsTable;
	
	@FXML
	TableColumn<Appointment, String> c_a_date;
	@FXML
	TableColumn<Appointment, String> c_a_time;
	@FXML
	TableColumn<Appointment, String> c_a_bookingclient;
	
	
	
	@FXML
	public void viewAppointments() {
		final ObservableList<Appointment> data =
		        FXCollections.observableArrayList();
		aTable.findAll().forEach(a -> {
			data.add(a);
		});
		System.out.println(data); //DEBUG
		appointmentsTable.setItems(data);
	}
	
	/* ********** *
     *  BARBIERI  *
     * ********** */
    final static BarbersTable bTable = new BarbersTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TableView<Barber> barbersTable;
	
	@FXML
	TableColumn<Barber, String> c_b_id;
	@FXML
	TableColumn<Barber, String> c_b_firstname;
	@FXML
	TableColumn<Barber, String> c_b_lastname;
	@FXML
	TableColumn<Barber, String> c_b_cf;
	@FXML
	TableColumn<Barber, String> c_b_birthdate;
	@FXML
	TableColumn<Barber, String> c_b_type;
	@FXML
	TableColumn<Barber, String> c_b_piva;
	
	@FXML
	public void viewBarbers() {
		final ObservableList<Barber> data =
		        FXCollections.observableArrayList();
		bTable.findAll().forEach(b -> {
			data.add(b);
		});
		barbersTable.setItems(data);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//CLIENTI
		viewCustomers();
		codcliente.setCellValueFactory(new PropertyValueFactory<>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		cognome.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		via.setCellValueFactory(new PropertyValueFactory<>("address"));
		città.setCellValueFactory(new PropertyValueFactory<>("city"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		telefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		//BARBIERI
		viewBarbers();
		c_b_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		c_b_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		c_b_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		c_b_cf.setCellValueFactory(new PropertyValueFactory<>("cf"));
		c_b_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		c_b_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		c_b_piva.setCellValueFactory(new PropertyValueFactory<>("piva"));
		
		//APPUNTAMENTI
		viewAppointments();
		c_a_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_a_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		c_a_bookingclient.setCellValueFactory(new PropertyValueFactory<>("idBookingClient"));
	}
}
