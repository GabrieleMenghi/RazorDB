package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.net.URL;

public class MainView extends Application {
	
	private final static String sep = System.getProperty("file.separator");
	private final static String home = System.getProperty("user.dir");

	@Override
	public void start(Stage stage) throws Exception {
		URL fxmlResource = getClass().getResource("/view/main.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlResource);
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.getIcons().add(new Image("images/logoRazor.jpg"));
		stage.setResizable(false);
        stage.setTitle("RazorDB");
        stage.show();
	}
}
