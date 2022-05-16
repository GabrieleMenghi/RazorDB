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
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void adminDialog(ActionEvent event) throws IOException {
		/*Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("Admin login");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);
		
		dialog.getDialogPane().setContent(grid);

		Optional<String> result = dialog.showAndWait();

		if(result.isPresent()) {
			if(result.get().equals("admin")) {
				System.out.println("OK");
			} else {
				System.out.println("Wrong password");
			}
		} else {
			System.out.println("Please, insert a password");
		}*/
		Stage s2 = (Stage) admin.getScene().getWindow();
		String path = "admin.fxml";
		root = FXMLLoader.load(getClass().getResource(path));
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		s2.close();
		stage.show();
		
	}
	
	@FXML
	public void clientDialog(ActionEvent event) throws IOException {
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
