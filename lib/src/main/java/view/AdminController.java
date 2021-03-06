package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import db.ConnectionProvider;
import db.tables.AppointmentsTable;
import db.tables.BarbersTable;
import db.tables.ClientsTable;
import db.tables.DetailedAppointmentsTable;
import db.tables.ReceiptsTable;
import db.tables.ServicesTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Appointment;
import model.Barber;
import model.Client;
import model.DetailedAppointment;
import model.Receipt;
import model.Service;
import utils.Utils;

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
	TableColumn<Client, String> c_c_id;
	@FXML
	TableColumn<Client, String> c_c_firstname;
	@FXML
	TableColumn<Client, String> c_c_lastname;
	@FXML
	TableColumn<Client, String> c_c_address;
	@FXML
	TableColumn<Client, String> c_c_city;
	@FXML
	TableColumn<Client, String> c_c_mail;
	@FXML
	TableColumn<Client, String> c_c_phone;
	
	@FXML
	TextField nameSearch;
	@FXML
	TextField faverage;
	
	
	
	@FXML
	public void viewCustomers() {
		final ObservableList<Client> data =
		        FXCollections.observableArrayList();
		cTable.findAll().forEach(c -> {
			data.add(c);
		});
		clientsTable.getItems().setAll(data);
	}
	
	@FXML
	public void refreshClients() {
		clientsTable.refresh();
		final ObservableList<Client> data =
		        FXCollections.observableArrayList();
		cTable.findAll().forEach(c -> {
			data.add(c);
		});
		clientsTable.getItems().setAll(data);
	}
	
	@FXML
	public void undoClients() {
		nameSearch.setText("");
		faverage.setText("");
		refreshClients();
	}

	@FXML
	public void createClient() throws IOException {
		Stage stage;
		Scene scene;
		Parent root;
		FXMLLoader loader = new FXMLLoader();
		URL fxmlurl = getClass().getResource("newClient.fxml");
		loader.setLocation(fxmlurl);
		root = loader.load();
		stage = new Stage();
		stage.getIcons().add(new Image("images/logoRazor.jpg"));
		stage.setTitle("Nuovo cliente");
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void deleteClient() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		int id = clientsTable.getSelectionModel().getSelectedItem().getId();
		alert.setTitle("Rimozione utente");
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
	
	@FXML
	public void updateClient() {
		Stage stage;
		Scene scene;
		Parent root;
		var sel = clientsTable.getSelectionModel().getSelectedItem();
		try {
			if(!sel.equals(null) && !(cTable.findFidelityById(sel.getId()) == null)) {
				FXMLLoader loader = new FXMLLoader();
				URL fxmlurl = getClass().getResource("updateClient.fxml");
				loader.setLocation(fxmlurl);
				root = (Parent) loader.load();
				UpdateClientController controller = loader.getController();
				controller.link(sel.getId(),
								sel.getFirstName(), 
								sel.getLastName(), 
								sel.getAddress(), 
								sel.getCity(), 
								sel.getMail(), 
								sel.getPhone(), 
								cTable.findFidelityById(sel.getId()).getCode(), 
								cTable.findFidelityById(sel.getId()).getBalance());
				stage = new Stage();
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Aggiorna cliente");
				stage.setResizable(false);
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else if(!(sel.equals(null)) && (cTable.findFidelityById(sel.getId())) == null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("updateClient.fxml"));
				root = (Parent) loader.load();
				UpdateClientController controller = loader.getController();
				controller.link(sel.getId(),
								sel.getFirstName(), 
								sel.getLastName(), 
								sel.getAddress(), 
								sel.getCity(), 
								sel.getMail(), 
								sel.getPhone(), 
								null, 
								null);
				stage = new Stage();
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Aggiorna cliente");
				stage.setResizable(false);
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e) {}
	}
	
	@FXML
	public void viewDetails() {
		Stage stage;
		Scene scene;
		Parent root;
		var sel = clientsTable.getSelectionModel().getSelectedItem();
		try {
			if(!sel.equals(null) && !(cTable.findFidelityById(sel.getId()) == null)) {
				FXMLLoader loader = new FXMLLoader();
				URL fxmlurl = getClass().getResource("viewDetails.fxml");
				loader.setLocation(fxmlurl);
				root = (Parent) loader.load();
				ClientDetailsController controller = loader.getController();
				controller.link(sel.getId(),
								sel.getFirstName(), 
								sel.getLastName(), 
								sel.getAddress(), 
								sel.getCity(), 
								sel.getMail(), 
								sel.getPhone(), 
								cTable.findFidelityById(sel.getId()).getCode(), 
								cTable.findFidelityById(sel.getId()).getBalance());
				stage = new Stage();
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Dettagli cliente");
				stage.setResizable(false);
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else if(!(sel.equals(null)) && (cTable.findFidelityById(sel.getId())) == null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("viewDetails.fxml"));
				root = (Parent) loader.load();
				ClientDetailsController controller = loader.getController();
				controller.link(sel.getId(),
								sel.getFirstName(), 
								sel.getLastName(), 
								sel.getAddress(), 
								sel.getCity(), 
								sel.getMail(), 
								sel.getPhone(), 
								null, 
								null);
				stage = new Stage();
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Dettagli cliente");
				stage.setResizable(false);
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e) {}
	}
	
	@FXML
	public void searchClientByName() throws SQLException {
		if(!nameSearch.getText().isEmpty()) {
			clientsTable.refresh();
			final ObservableList<Client> data =
					FXCollections.observableArrayList();
			cTable.viewClientsByName(nameSearch.getText()).forEach(c -> {
				data.add(c);
			});
			clientsTable.getItems().setAll(data);
		}
	}
	
	@FXML
	public void newFidelity() {
		Integer res = BalanceBox.display("Nuova fidelity", "Inserire saldo punti iniziale");
		if(!(res == null)) {
			cTable.addFidelityById(clientsTable.getSelectionModel().getSelectedItem().getId(), res);
		}
	}
	
	@FXML
	public void newAppointment() {
		Pair<Appointment, List<String>> res = AppointmentBox.display("Nuovo appuntamento");
		if(!(res == null)) {
			cTable.addAppointmentById(clientsTable.getSelectionModel().getSelectedItem().getId(), res.getKey(), res.getValue());
		}
	}
	
	@FXML
	public void clientsOverAverage() {
		Double average = null;
		try {
			average = Double.parseDouble(faverage.getText());
		} catch (Exception e) {
			average = null;
		}
		if(!(average == null)) {
			clientsTable.refresh();
			final ObservableList<Client> data =
					FXCollections.observableArrayList();
			cTable.viewClientsOverAverage(average).forEach(c -> {
				data.add(c);
			});
			clientsTable.getItems().setAll(data);
		}
	}
	
	@FXML
	public void topPremiumClients() {
		clientsTable.refresh();
		final ObservableList<Client> data =
				FXCollections.observableArrayList();
		cTable.viewTop3PremiumClients().forEach(c -> {
			data.add(c);
		});
		clientsTable.getItems().setAll(data);
	}
	
	/* *************** *
     *   APPUNTAMENTI  *
     * *************** */
	final static DetailedAppointmentsTable daTable = new DetailedAppointmentsTable(connectionProvider.getMySQLConnection());
	final static AppointmentsTable aTable = new AppointmentsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TableView<DetailedAppointment> appointmentsTable;
	
	@FXML
	TableColumn<DetailedAppointment, String> c_a_date;
	@FXML
	TableColumn<DetailedAppointment, String> c_a_time;
	@FXML
	TableColumn<DetailedAppointment, String> c_a_bookingclient;
	@FXML
	TableColumn<DetailedAppointment, String> c_a_clientfirstname;
	@FXML
	TableColumn<DetailedAppointment, String> c_a_clientlastname;
	@FXML
	DatePicker date;
	
	@FXML
	public void viewAppointments() {
		final ObservableList<DetailedAppointment> data =
		        FXCollections.observableArrayList();
		daTable.findAll().forEach(da -> {
			data.add(da);
		});
		appointmentsTable.setItems(data);
	}

	private void refreshAppointments() {
		appointmentsTable.refresh();
		final ObservableList<DetailedAppointment> data =
		        FXCollections.observableArrayList();
		daTable.findAll().forEach(da -> {
			data.add(da);
		});
		appointmentsTable.getItems().setAll(data);
	}
	
	@FXML
	public void viewAppByDate() {
		Date d = Utils.buildDate(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear()).get();
		final ObservableList<DetailedAppointment> data =
		        FXCollections.observableArrayList();
		daTable.findAppointmentsByDate(Utils.dateToSqlDate(d)).forEach(a -> {
			data.add(a);
		});
		appointmentsTable.getItems().setAll(data);
	}
	
	@FXML
	public void undo() {
		date.setValue(LocalDate.now());
		date.getEditor().clear();
		viewAppointments();
	}
	
	@FXML
	public void createAppointment() throws IOException {
		Stage stage;
		Scene scene;
		Parent root;
		FXMLLoader loader = new FXMLLoader();
		URL fxmlurl = getClass().getResource("newAppointment.fxml");
		loader.setLocation(fxmlurl);
		root = loader.load();
		stage = new Stage();
		stage.getIcons().add(new Image("images/logoRazor.jpg"));
		stage.setTitle("Nuovo appuntamento");
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void updateAppointment() {
		DetailedAppointment old = appointmentsTable.getSelectionModel().getSelectedItem();
		Pair<java.sql.Date, Time> res = UpdateAppointmentBox.display("Aggiorna appuntamento", old.getTime().toString());
		if(!(res == null)) {
			aTable.updateAppointment(old.getIdBarber(), old.getDate(), old.getTime(), res.getKey(), res.getValue());
			refreshAppointments();
		}
	}
	
	@FXML
	public void getServices() {
		DetailedAppointment app = appointmentsTable.getSelectionModel().getSelectedItem();
		ServicesBox.display("Servizi", aTable.getAppointmentServices(app.getIdBarber(), app.getDate(), app.getTime()));
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
	
	/* *************** *
     *    SCONTRINI    *
     * *************** */
	final static ReceiptsTable rTable = new ReceiptsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TableView<Receipt> receiptsTable;
	
	@FXML
	TableColumn<Receipt, String> c_r_id;
	@FXML
	TableColumn<Receipt, String> c_r_date;
	@FXML
	TableColumn<Receipt, String> c_r_time;
	@FXML
	TableColumn<Receipt, String> c_r_total;
	@FXML
	TableColumn<Receipt, String> c_r_barber;
	@FXML
	TableColumn<Receipt, String> c_r_client;
	
	
	
	@FXML
	public void viewReceipts() {
		final ObservableList<Receipt> data =
		        FXCollections.observableArrayList();
		rTable.findAll().forEach(r -> {
			data.add(r);
		});
		receiptsTable.setItems(data);
	}
	
	/* ********** *
     *   SERVIZI  *
     * ********** */
    final static ServicesTable serTable = new ServicesTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TableView<Service> servicesTable;
	
	@FXML
	TableColumn<Service, String> ser_c_name;
	@FXML
	TableColumn<Service, String> ser_c_description;
	@FXML
	TableColumn<Service, String> ser_c_price;
	@FXML
	TableColumn<Service, String> ser_c_duration;
	@FXML
	TableColumn<Service, String> ser_c_req1;
	@FXML
	TableColumn<Service, String> ser_c_req2;
	@FXML
	TableColumn<Service, String> ser_req3;
	@FXML
	TableColumn<Service, String> ser_req4;
	
	
	
	@FXML
	public void viewServices() {
		final ObservableList<Service> data =
		        FXCollections.observableArrayList();
		serTable.findAll().forEach(s -> {
			data.add(s);
		});
		servicesTable.getItems().setAll(data);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//CLIENTI
		viewCustomers();
		c_c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		c_c_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		c_c_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		c_c_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		c_c_city.setCellValueFactory(new PropertyValueFactory<>("city"));
		c_c_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		c_c_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
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
		c_a_bookingclient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		c_a_clientfirstname.setCellValueFactory(new PropertyValueFactory<>("clientFirstName"));
		c_a_clientlastname.setCellValueFactory(new PropertyValueFactory<>("clientLastName"));
		
		//SCONTRINI
		viewReceipts();
		c_r_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		c_r_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_r_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		c_r_total.setCellValueFactory(new PropertyValueFactory<>("total"));
		c_r_barber.setCellValueFactory(new PropertyValueFactory<>("idBarber"));
		c_r_client.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		
		//SERVIZI
		viewServices();
		ser_c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ser_c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		ser_c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		ser_c_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		ser_c_req1.setCellValueFactory(new PropertyValueFactory<>("requests1"));
		ser_c_req2.setCellValueFactory(new PropertyValueFactory<>("requests2"));
		ser_req3.setCellValueFactory(new PropertyValueFactory<>("requests3"));
		ser_req4.setCellValueFactory(new PropertyValueFactory<>("requests4"));
	}
}
