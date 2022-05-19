package view;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import db.ConnectionProvider;
import db.tables.AppointmentsTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import utils.Utils;

public class NewAppointmentController {
	final static String username = "root";
    final static String password = "";
    final static String dbName = "razordb";
    
    final static ConnectionProvider connectionProvider = new ConnectionProvider(username, password, dbName);
    final static AppointmentsTable aTable = new AppointmentsTable(connectionProvider.getMySQLConnection());
	
	@FXML
	TextField fperformingBarber;
	@FXML
	DatePicker pdate;
	@FXML
	TextField ftime;
	@FXML
	TextField fbookingClient;
	@FXML
	TextField fperformingClient;
	@FXML
	TextField fbookingBarber;
	@FXML
	TextField freceiptNumber;
	@FXML
	DatePicker preceiptDate;
	@FXML
	Button save;
	@FXML
	Button close;
	@FXML
	Label error;
	
	@SuppressWarnings("deprecation")
	@FXML
	public void saveAppointment() {
		boolean err1 = false;
		boolean err2 = false;
		try {
			pdate.getValue().equals(null);
		} catch (NullPointerException e) {
			err1 = true;
			error.setText("Compila i campi obbligatori");
		}
		if(fperformingBarber.getText().isEmpty() || err1 || ftime.getText().isEmpty() || fbookingClient.getText().isEmpty() || fbookingBarber.getText().isEmpty()) {
			error.setText("Compila i campi obbligatori");
		} else {
			Stage s2 = (Stage) save.getScene().getWindow();
			Appointment a;
			Date d = Utils.buildDate(pdate.getValue().getDayOfMonth(), pdate.getValue().getMonthValue(), pdate.getValue().getYear()).get();
			try {
				preceiptDate.getValue().equals(null);
			} catch (NullPointerException e) {
				err2 = true;
			}
			if(err2) {
				a = new Appointment(getInteger(fperformingBarber.getText()), 
						Utils.dateToSqlDate(d),
						Time.valueOf(ftime.getText()), 
						getInteger(fbookingClient.getText()), 
						getInteger(fperformingClient.getText()),
						getInteger(fbookingBarber.getText()),
						getInteger(freceiptNumber.getText()),
						null);
						aTable.save(a);
			} else {
				Date d1 = Utils.buildDate(preceiptDate.getValue().getDayOfMonth(), preceiptDate.getValue().getMonthValue(), preceiptDate.getValue().getYear()).get();
				a = new Appointment(getInteger(fperformingBarber.getText()), 
						Utils.dateToSqlDate(d), 
						Time.valueOf(ftime.getText()), 
						getInteger(fbookingClient.getText()), 
						getInteger(fperformingClient.getText()),
						getInteger(fbookingBarber.getText()),
						getInteger(freceiptNumber.getText()),
						Utils.dateToSqlDate(d1));
						aTable.save(a);
			}
			s2.close();
		}
		
	}
	
	@FXML
	public void closeStage() {
		Stage s2 = (Stage) close.getScene().getWindow();
		s2.close();
	}
	
	private Integer getInteger(String text) {
		if(text.isEmpty()) {
			return null;
		} else {
			return Integer.parseInt(text);
		}
	}
}
