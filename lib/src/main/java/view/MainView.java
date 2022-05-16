package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;

public class MainView extends Application {
	
	private final static String sep = System.getProperty("file.separator");
	private final static String home = System.getProperty("user.dir");

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		String path = home + sep + "src" + sep + "main" + sep
				+ "resources" + sep + "view" + sep + "main.fxml";
		try(FileInputStream fxmlStream = new FileInputStream(path)){
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(scene);
			stage.getIcons().add(new Image("images/logoRazor.jpg"));
		} catch (Exception e) {}
        stage.setTitle("RazorDB");
        stage.show();
	}
}
