package view;

import java.io.IOException;
import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	Button admin;
	
	@FXML
	Button cliente;
	
	@FXML
	AnchorPane mainPane;
	
	@FXML
	PasswordField adminPassword;
	
	@FXML
	TextField clientCode;
	
	@FXML
	Label adminLabel;
	
	@FXML
	Label clientLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void adminAccess(ActionEvent event) throws IOException {
		clientLabel.setText("");
		if(adminPassword.getText().isEmpty()) {
			adminLabel.setText("Inserisci una password");
		} else {
			if(adminPassword.getText().equals("admin")) {
				adminLabel.setText("");
				Stage s2 = (Stage) admin.getScene().getWindow();
				String path = "admin.fxml";
				root = FXMLLoader.load(getClass().getResource(path));
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				s2.close();
				stage.show();
			} else {
				adminLabel.setText("Password errata");
			}
		}
		
	}
	
	@FXML
	public void clientAccess(ActionEvent event) throws IOException {
		adminLabel.setText("");
		try {
			if(cTable.isClientPresent(Integer.parseInt(clientCode.getText()))) {
				clientLabel.setText("");
				Stage s2 = (Stage) cliente.getScene().getWindow();
				String path = "cliente.fxml";
				root = FXMLLoader.load(getClass().getResource(path));
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				s2.close();
				stage.show();
			} else {
				clientLabel.setText("Il cliente non è presente");
			}
		} catch (Exception e){
			clientLabel.setText("Inserire un codice valido");
		}
	}

}
