package view;

import java.net.URL;
import java.util.ResourceBundle;
import db.ConnectionProvider;
import db.tables.ClientAppointmentsTable;
import db.tables.ClientsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.ClientAppointment;

public class ClientController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
	
	private Integer clientCode;
	
	public void setCode(final Integer code) {
		this.clientCode = code;
		final ObservableList<ClientAppointment> data =
		        FXCollections.observableArrayList();
		aTable.findAll(clientCode).forEach(da -> {
			data.add(da);
		});
		appointmentsTable.setItems(data);
		
		ffirstname.setText(cTable.findClientById(clientCode).getFirstName());
		flastname.setText(cTable.findClientById(clientCode).getLastName());
		faddress.setText(cTable.findClientById(clientCode).getAddress());
		fcity.setText(cTable.findClientById(clientCode).getCity());
		fmail.setText(cTable.findClientById(clientCode).getMail());
		if(cTable.findClientById(clientCode).getPhone() == null) {
			fphone.setText("");
		} else {
			fphone.setText(String.valueOf(cTable.findClientById(clientCode).getPhone()));
		}
	}
	
   /* *************** *
    *   APPUNTAMENTI  *
    * *************** */
    
	final static ClientAppointmentsTable aTable = new ClientAppointmentsTable(connectionProvider.getMySQLConnection());

	@FXML
	TableView<ClientAppointment> appointmentsTable;
	
	@FXML
	TableColumn<ClientAppointment, String> c_a_date;
	@FXML
	TableColumn<ClientAppointment, String> c_a_time;
	@FXML
	TableColumn<ClientAppointment, String> c_a_bookingbarber;
	@FXML
	TableColumn<ClientAppointment, String> c_a_receiptnumber;
	@FXML
	TableColumn<ClientAppointment, String> c_a_receiptdate;
	
	@FXML
	public void viewAppointments() {
		final ObservableList<ClientAppointment> data =
		        FXCollections.observableArrayList();
		aTable.findAll(clientCode).forEach(da -> {
			data.add(da);
		});
		appointmentsTable.getItems().setAll(data);
	}
	
	/* *************** *
	 *      DATI       *
	 * *************** */
	final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TextField ffirstname;
	@FXML
	TextField flastname;
	@FXML
	TextField faddress;
	@FXML
	TextField fcity;
	@FXML
	TextField fmail;
	@FXML
	TextField fphone;
	
	@FXML
	public void save() {
		Client newClient = new Client(clientCode, 
										ffirstname.getText(), 
										flastname.getText(), 
										getString(faddress.getText()), 
										getString(fcity.getText()), 
										getString(fmail.getText()), 
										getLong(fphone.getText()));
		cTable.updateClient(newClient);
	}
	
	private String getString(String text){
		if(text == null || text.isEmpty() || text.isBlank()) {
			return null;
		}
		return text;
	}
	
	private Long getLong(String text){
		if(text == null || text.isEmpty() || text.isBlank()) {
			return null;
		}
		return Long.decode(text);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//APPUNTAMENTI
		c_a_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		c_a_time.setCellValueFactory(new PropertyValueFactory<>("time"));
		c_a_bookingbarber.setCellValueFactory(new PropertyValueFactory<>("bookingBarber"));
		c_a_receiptnumber.setCellValueFactory(new PropertyValueFactory<>("receiptNumber"));
		c_a_receiptdate.setCellValueFactory(new PropertyValueFactory<>("receiptDate"));
	}	
}
