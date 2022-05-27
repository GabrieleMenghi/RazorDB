package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.ConnectionProvider;
import db.tables.ClientsTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MainController implements Initializable{
	
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static ClientsTable cTable = new ClientsTable(connectionProvider.getMySQLConnection());
    
    private final static String sep = System.getProperty("file.separator");
	private final static String home = System.getProperty("user.dir");
	
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
				FXMLLoader loader = new FXMLLoader();
				URL fxmlurl = getClass().getResource("admin.fxml");
				loader.setLocation(fxmlurl);
				root = loader.load(); 
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
				FXMLLoader loader = new FXMLLoader();
				URL fxmlurl = getClass().getResource("cliente.fxml");
				loader.setLocation(fxmlurl);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    clientCode.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });		
	}
}
