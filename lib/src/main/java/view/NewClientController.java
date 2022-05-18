package view;

import java.sql.SQLException;
import java.util.Optional;

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
			Client c = new Client(ffirstname.getText(), 
									flastname.getText(), 
									Optional.ofNullable(faddress.getText()), 
									Optional.ofNullable(fcity.getText()), 
									Optional.ofNullable(fmail.getText()), 
									Optional.ofNullable(Long.decode(fphone.getText())));
			cTable.save(c);
			s2.close();
		}
	}
	
	@FXML
	public void closeStage() {
		Stage s2 = (Stage) close.getScene().getWindow();
		s2.close();
	}
}
