package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BalanceBox {
	
	static Integer res = null;
	
	public static Integer display(final String title, final String description) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(description);
		Label err = new Label();
		err.setTextFill(Color.RED);
		
		TextField tf = new TextField();
		tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
		Button save = new Button("Save");
		Button close = new Button("Close");
		
		save.setOnAction(e -> {
			if(tf.getText().isEmpty() || tf.getText().isBlank()) {
				err.setText("Inserire un valore");
			} else {
				res = Integer.parseInt(tf.getText());
				window.close();
			}
		});
		
		close.setOnAction(e -> {
			window.close();
			res = null;
		});
		
		VBox box = new VBox(10);
		HBox hbox = new HBox(10); 
		hbox.getChildren().addAll(save, close);
		hbox.setAlignment(Pos.CENTER);
		box.getChildren().addAll(label, err, tf, hbox);
		box.setAlignment(Pos.CENTER);
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
		return res;
	}

}
