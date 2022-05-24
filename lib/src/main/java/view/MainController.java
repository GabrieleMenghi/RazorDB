package view;

import java.io.IOException;
import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

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
	

	
	@FXML
	public void adminAccess(ActionEvent event) throws IOException {
		Stage stage;
		Scene scene;
		Parent root;
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
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Admin");
				stage.show();
			} else {
				adminLabel.setText("Password errata");
			}
		}
		
	}
	
	@FXML
	public void clientAccess(ActionEvent event) throws IOException {
		Stage stage;
		Scene scene;
		Parent root;
		adminLabel.setText("");
		try {
			final Integer code = Integer.parseInt(clientCode.getText());
			if(cTable.isClientPresent(code)) {
				clientLabel.setText("");
				Stage s2 = (Stage) cliente.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente.fxml"));
				root = (Parent) loader.load();
				ClientController clientController = loader.getController();
				clientController.setCode(code);
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				s2.close();
				stage.getIcons().add(new Image("images/logoRazor.jpg"));
				stage.setTitle("Cliente");
				stage.show();
			} else {
				clientLabel.setText("Il cliente non è presente");
			}
		} catch (Exception e){
			clientLabel.setText("Inserire un codice valido");
		}
	}
	
	public void enterAdmin() {
		adminPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent k) {
	            if (k.getCode().equals(KeyCode.ENTER)) {
	                try {
						adminAccess(null);
					} catch (IOException e) {}
	            }
	        }
		});
	}
	
	public void enterClient() {
		clientCode.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent k) {
	            if (k.getCode().equals(KeyCode.ENTER)) {
	                try {
						clientAccess(null);
					} catch (IOException e) {}
	            }
	        }
		});
	}
}
