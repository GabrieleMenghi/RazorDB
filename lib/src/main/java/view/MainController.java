package view;

import java.io.IOException;
import java.util.Optional;

import org.junit.platform.engine.support.hierarchical.Node;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainController {
	
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
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void adminAccess(ActionEvent event) throws IOException {
		if(adminPassword.getText().equals("")) {
			adminLabel.setText("Please, insert the password");
		} else {
			if(adminPassword.getText().equals("admin")) {
				Stage s2 = (Stage) admin.getScene().getWindow();
				String path = "admin.fxml";
				root = FXMLLoader.load(getClass().getResource(path));
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				s2.close();
				stage.show();
			} else {
				adminLabel.setText("Wrong password");
			}
		}
		
	}
	
	@FXML
	public void clientAccess(ActionEvent event) throws IOException {
		Stage s2 = (Stage) cliente.getScene().getWindow();
		String path = "cliente.fxml";
		root = FXMLLoader.load(getClass().getResource(path));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		s2.close();
		stage.show();
		
	}

}
