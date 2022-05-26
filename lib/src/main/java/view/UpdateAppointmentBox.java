package view;

import java.sql.Time;
import java.sql.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class UpdateAppointmentBox {
	
	static Pair<Date, Time> res;
	
	public static Pair<Date, Time> display(final String title, final String oldTime) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.getIcons().add(new Image("images/logoRazor.jpg"));
		window.setResizable(false);
		Label fields = new Label();
		fields.setText("* = campo obbligatorio");
		fields.getStyleClass().add("labels");
		Label err = new Label();
		err.getStyleClass().add("error");
		
		//Data
		DatePicker date = new DatePicker();
		date.getStyleClass().add("datetext");
		Label ldate = new Label(" Data*");
		ldate.getStyleClass().add("labels");
		HBox hdate = new HBox(10);
		hdate.getChildren().addAll(ldate, date);
		HBox.setMargin(date, new Insets(0, 20, 0, 0));
		
		//Ora
		TextField ftime = new TextField();
		ftime.getStyleClass().add("textfield");
		ftime.setText(oldTime);
		Label ltime = new Label(" Ora* (HH:MM:SS)");
		ltime.getStyleClass().add("labels");
		HBox htime = new HBox(10);
		htime.getChildren().addAll(ltime, ftime);
		HBox.setMargin(ftime, new Insets(0, 20, 0, 0));
		
		Button save = new Button("Save");
		Button close = new Button("Close");
		
		save.setOnAction(e -> {
			if(date.getValue() == null || ftime.getText().isEmpty() || ftime.getText().isBlank()) {
				err.setText("Compila i campi obbligatori");
			} else {
				res = new Pair<Date, Time>(Date.valueOf(date.getValue()), Time.valueOf(ftime.getText()));
				window.close();
			}
		});
		
		close.setOnAction(e -> {
			res = null;
			window.close();
		});
		
		VBox box = new VBox(10);
		HBox hbox = new HBox(10); 
		hbox.getChildren().addAll(save, close);
		hbox.setAlignment(Pos.CENTER);
		box.getChildren().addAll(fields, err, hdate, htime, hbox);
		box.setAlignment(Pos.CENTER);
		box.setMinWidth(350);
		box.setMinHeight(300);
		Scene scene = new Scene(box);
		scene.getStylesheets().add(AppointmentBox.class.getResource("style3.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
		return res;
	}
}
