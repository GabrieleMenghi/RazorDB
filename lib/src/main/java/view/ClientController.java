package view;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import db.ConnectionProvider;
import db.tables.ClientAppointmentsTable;
import db.tables.DetailedAppointmentsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClientAppointment;
import model.DetailedAppointment;

public class ClientController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
	
	private Integer clientCode;
	
	public void setCode(final Integer code) {
		this.clientCode = code;
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
		appointmentsTable.setItems(data);
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
