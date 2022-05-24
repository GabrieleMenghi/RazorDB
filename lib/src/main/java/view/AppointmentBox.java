package view;

import java.sql.Time;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Appointment;
import utils.Utils;

public class AppointmentBox {
	
	static Appointment res = null;
	
	public static Appointment display(final String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		window.setMinHeight(300);
		window.getIcons().add(new Image("images/logoRazor.jpg"));
		Label fields = new Label();
		fields.setText("* = campo obbligatorio");
		Label err = new Label();
		err.setTextFill(Color.RED);
		
		//Barbiere prenotante
		TextField fbookingbarber = new TextField();
		fbookingbarber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    fbookingbarber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
		Label lbookingbarber = new Label("Barbiere prenotante*");
		HBox hbookingbarber = new HBox(10);
		hbookingbarber.getChildren().addAll(lbookingbarber, fbookingbarber);
		//Data
		DatePicker date = new DatePicker();
		Label ldate = new Label("Data*");
		HBox hdate = new HBox(10);
		hdate.getChildren().addAll(ldate, date);
		//Ora
		TextField ftime = new TextField();
		Label ltime = new Label("Ora*");
		HBox htime = new HBox(10);
		htime.getChildren().addAll(ltime, ftime);
		//Barbiere effettuante
		TextField fperformingbarber = new TextField();
		fperformingbarber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    fperformingbarber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
		Label lperformingbarber = new Label("Barbiere effettuante*");
		HBox hperformingbarber = new HBox(10);
		hperformingbarber.getChildren().addAll(lperformingbarber, fperformingbarber);
		
		Button save = new Button("Save");
		Button close = new Button("Close");
		
		save.setOnAction(e -> {
			if(fbookingbarber.getText().isEmpty() || fbookingbarber.getText().isBlank() || date.getValue() == null || ftime.getText().isEmpty() 
				|| ftime.getText().isBlank() || fperformingbarber.getText().isEmpty() || fperformingbarber.getText().isBlank()) {
				err.setText("Compila i campi obbligatori");
			} else {
				Date d = Utils.buildDate(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear()).get();
				res = new Appointment(Integer.parseInt(fperformingbarber.getText()), 
										Utils.dateToSqlDate(d), 
										Time.valueOf(ftime.getText()), 
										null,
										null, 
										Integer.parseInt(fbookingbarber.getText()), 
										null, 
										null);
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
		box.getChildren().addAll(fields, err, hbookingbarber, hdate, htime, hperformingbarber, hbox);
		box.setAlignment(Pos.CENTER);
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
		return res;
	}
}
