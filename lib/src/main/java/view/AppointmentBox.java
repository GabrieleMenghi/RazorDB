package view;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Appointment;
import utils.Utils;

public class AppointmentBox {
	
	static Pair<Appointment, List<String>> res = null;
	
	public static Pair<Appointment, List<String>> display(final String title) {
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
		
		//Barbiere prenotante
		TextField fbookingbarber = new TextField();
		fbookingbarber.getStyleClass().add("textfield");
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
		Label lbookingbarber = new Label(" Barbiere prenotante*");
		lbookingbarber.getStyleClass().add("labels");
		HBox hbookingbarber = new HBox(10);
		hbookingbarber.getChildren().addAll(lbookingbarber, fbookingbarber);
		HBox.setMargin(fbookingbarber, new Insets(0, 20, 0, 0));
		
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
		Label ltime = new Label(" Ora* (HH:MM:SS)");
		ltime.getStyleClass().add("labels");
		HBox htime = new HBox(10);
		htime.getChildren().addAll(ltime, ftime);
		HBox.setMargin(ftime, new Insets(0, 20, 0, 0));
		
		//Barbiere effettuante
		TextField fperformingbarber = new TextField();
		fperformingbarber.getStyleClass().add("textfield");
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
		Label lperformingbarber = new Label(" Barbiere effettuante*");
		lperformingbarber.getStyleClass().add("labels");
		HBox hperformingbarber = new HBox(10);
		hperformingbarber.getChildren().addAll(lperformingbarber, fperformingbarber);
		HBox.setMargin(fperformingbarber, new Insets(0, 20, 0, 0));
		
		//Servizi
		Label lservices = new Label(" Servizi*");
		lservices.getStyleClass().add("labels");
		VBox vservices = new VBox(10);
		HBox secondrow = new HBox();
		VBox firstcolumn = new VBox();
		VBox secondcolumn = new VBox();
		secondrow.getChildren().addAll(firstcolumn, secondcolumn);
		CheckBox bp = new CheckBox("Barba premium");
		CheckBox bs = new CheckBox("Barba standard");
		CheckBox cl = new CheckBox("Colore");
		CheckBox mb = new CheckBox("Modellatura barba");
		CheckBox mv = new CheckBox("Maschera viso");
		CheckBox pc = new CheckBox("Piastra capelli");
		CheckBox rs = new CheckBox("Rifinitura sopracciglia");
		CheckBox tb = new CheckBox("Taglio bimbo");
		CheckBox tp = new CheckBox("Taglio premium");
		CheckBox ts = new CheckBox("Taglio standard");
		//Set margins
		double margin = 10;
		VBox.setMargin(bp, new Insets(margin, margin, 0, margin));
		VBox.setMargin(bs, new Insets(margin, margin, 0, margin));
		VBox.setMargin(cl, new Insets(margin, margin, 0, margin));
		VBox.setMargin(mb, new Insets(margin, margin, 0, margin));
		VBox.setMargin(mv, new Insets(margin, margin, 0, margin));
		VBox.setMargin(pc, new Insets(margin, margin, 0, margin));
		VBox.setMargin(rs, new Insets(margin, margin, 0, margin));
		VBox.setMargin(tb, new Insets(margin, margin, 0, margin));
		VBox.setMargin(tp, new Insets(margin, margin, 0, margin));
		VBox.setMargin(ts, new Insets(margin, margin, 0, margin));
		
		firstcolumn.setPrefWidth(225);
		secondcolumn.setPrefWidth(225);
		firstcolumn.getChildren().addAll(bp, bs, cl, mb, mv);
		secondcolumn.getChildren().addAll(pc, rs, tb, tp, ts);
		vservices.getChildren().addAll(lservices, secondrow);
		
		Button save = new Button("Save");
		Button close = new Button("Close");
		
		save.setOnAction(e -> {
			List<String> services = new ArrayList<>();
			if(fbookingbarber.getText().isEmpty() || fbookingbarber.getText().isBlank() || date.getValue() == null || ftime.getText().isEmpty() 
				|| ftime.getText().isBlank() || fperformingbarber.getText().isEmpty() || fperformingbarber.getText().isBlank()
				|| (!bp.isSelected() && !bs.isSelected() && !cl.isSelected() && !mb.isSelected() && !mv.isSelected() && !pc.isSelected() 
						&& !rs.isSelected() && !tb.isSelected() && !tp.isSelected() && !ts.isSelected())) {
				err.setText("Compila i campi obbligatori");
			} else {
				Date d = Utils.buildDate(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear()).get();
				if(bp.isSelected()) {
					services.add("BP");
				}
				if(bs.isSelected()) {
					services.add("BS");
				}
				if(cl.isSelected()) {
					services.add("CL");
				}
				if(mb.isSelected()) {
					services.add("MB");
				}
				if(mv.isSelected()) {
					services.add("MV");
				}
				if(pc.isSelected()) {
					services.add("PC");
				}
				if(rs.isSelected()) {
					services.add("RS");
				}
				if(tb.isSelected()) {
					services.add("TB");
				}
				if(tp.isSelected()) {
					services.add("tp");
				}
				if(ts.isSelected()) {
					services.add("TS");
				}
				res = new Pair<>(new Appointment(Integer.parseInt(fperformingbarber.getText()), 
										Utils.dateToSqlDate(d), 
										Time.valueOf(ftime.getText()), 
										null,
										null, 
										Integer.parseInt(fbookingbarber.getText()), 
										null, 
										null), 
								services);
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
		box.getChildren().addAll(fields, err, hbookingbarber, hdate, htime, hperformingbarber, vservices, hbox);
		box.setAlignment(Pos.CENTER);
		box.setPrefWidth(450);
		box.setMinWidth(350);
		box.setPrefHeight(600);
		box.setMinHeight(300);
		Scene scene = new Scene(box);
		scene.getStylesheets().add(AppointmentBox.class.getResource("style3.css").toExternalForm());
		window.setScene(scene);
		window.showAndWait();
		return res;
	}
}
