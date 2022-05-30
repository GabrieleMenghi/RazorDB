package view;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServicesBox {
	
	public static void display(final String title, final List<String> services) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.getIcons().add(new Image("images/logoRazor.jpg"));
		window.setResizable(false);
		
		Button close = new Button("Close");
		
		close.setOnAction(e -> {
			window.close();
		});
		
		VBox box = new VBox(10);
		for(String s : services) {
			HBox sbox = new HBox();
			sbox.setAlignment(Pos.CENTER_LEFT);
			Label l = new Label(s);
			l.getStyleClass().add("labels");
			sbox.getChildren().add(l);
			box.getChildren().add(sbox);
		}
		
		HBox hbox = new HBox(10); 
		hbox.getChildren().addAll(close);
		hbox.setAlignment(Pos.CENTER);
		box.getChildren().addAll(hbox);
		box.setAlignment(Pos.CENTER);
		box.setMinWidth(250);
		Scene scene = new Scene(box);
		scene.getStylesheets().add(BalanceBox.class.getResource("style3.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
	}
}
