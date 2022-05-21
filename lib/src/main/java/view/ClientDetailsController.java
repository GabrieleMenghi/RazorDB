package view;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;
import model.Fidelity;

public class ClientDetailsController {
	@FXML
	Button viewAll;
	@FXML
	Button save;
	@FXML
	Button close;
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
	private Integer id;
    private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String mail;
	private Long phone;
	private Integer fidelityNumber;
	private Integer balance;
	
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
	TextField ffidelitynumber;
	@FXML
	TextField fbalance;

	public void link(Integer id, String firstName, String lastName, String address, String city, String mail,
						Long phone, Integer fidelityNumber, Integer balance) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.mail = mail;
		this.phone = phone;
		this.fidelityNumber = fidelityNumber;
		this.balance = balance;
	}


	@FXML
	public void init() {
		if(address == null) {
			faddress.setText("");
		} else {
			faddress.setText(address);
		}
		if(city == null) {
			fcity.setText("");
		} else {
			fcity.setText(city);
		}
		if(mail == null) {
			fmail.setText("");
		} else {
			fmail.setText(mail);
		}
		if(phone == null) {
			fphone.setText("");
		} else {
			fphone.setText(String.valueOf(phone));
		}
		if(fidelityNumber == null) {
			ffidelitynumber.setText("");
		} else {
			ffidelitynumber.setText(String.valueOf(fidelityNumber));
		}
		if(balance == null) {
			fbalance.setText("");
		} else {
			fbalance.setText(String.valueOf(balance));
		}
		ffirstname.setText(firstName);
		flastname.setText(lastName);
	}
	
	@FXML
	public void closeStage() {
		Stage s2 = (Stage) close.getScene().getWindow();
		s2.close();
	}	
}
