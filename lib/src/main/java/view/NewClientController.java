package view;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

public class NewClientController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
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
	Button save;
	@FXML
	Button close;
	@FXML
	Label error;
	
	@FXML
	public void saveClient() {
		if(ffirstname.getText().isEmpty() || flastname.getText().isEmpty()) {
			error.setText("Compila i campi obbligatori");
		} else {
			Stage s2 = (Stage) save.getScene().getWindow();
			Client c;
			c = new Client(ffirstname.getText(), 
							flastname.getText(), 
							getString(faddress.getText()), 
							getString(fcity.getText()), 
							getString(fmail.getText()), 
							getLong(fphone.getText()));
			cTable.save(c);
			
			s2.close();
		}
	}
	
	@FXML
	public void closeStage() {
		Stage s2 = (Stage) close.getScene().getWindow();
		s2.close();
	}
	
	private String getString(String text){
		if(text.isEmpty()) {
			return null;
		}
		return text;
	}
	
	private Long getLong(String text){
		if(text.isEmpty() || text == null) {
			return null;
		}
		return Long.decode(text);
	}
}
