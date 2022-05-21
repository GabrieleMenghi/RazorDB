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

public class UpdateClientController {
	
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
	@FXML
	Button save;
	@FXML
	Button close;
	@FXML
	Label error;
	@FXML
	Button init;

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
		if(fidelityNumber == null) {
			fbalance.setEditable(false);
		}
	}
	
	@FXML
	public void save() {
		Stage s2 = (Stage) save.getScene().getWindow();
		if(ffirstname.getText().isEmpty() || flastname.getText().isEmpty()) {
			error.setText("Compila i campi obbligatori");
		} else {
			System.out.println(faddress.getText());
			Client c;
			Fidelity f;
			c = new Client(this.id,
							ffirstname.getText(), 
							flastname.getText(), 
							getString(faddress.getText()), 
							getString(fcity.getText()), 
							getString(fmail.getText()), 
							getLong(fphone.getText()));
			f = new Fidelity(this.fidelityNumber, 
								getInteger(fbalance.getText()), 
								this.id);
			System.out.println(c);
			System.out.println(f);
			cTable.updateClient(c);
			cTable.updateFidelity(f);
			s2.close();
		}
	}
	
	@FXML
	public void closeStage() {
		Stage s2 = (Stage) close.getScene().getWindow();
		s2.close();
	}
	
	private String getString(String text){
		if(text.isEmpty() || text == null || text.length() == 0) {
			return null;
		}
		return text;
	}
	
	private Integer getInteger(String text){
		if(text.isEmpty() || text == null || text.length() == 0) {
			return null;
		}
		return Integer.parseInt(text);
	}
	
	private Long getLong(String text){
		if(text.isEmpty() || text == null || text.length() == 0) {
			return null;
		}
		return Long.decode(text);
	}
}
