package view;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.ConnectionProvider;
import db.tables.AppointmentsTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	Button save;
	@FXML
	Button close;
	@FXML
	Label error;
	
	@FXML
	CheckBox bp;
	@FXML
	CheckBox bs;
	@FXML
	CheckBox cl;
	@FXML
	CheckBox mb;
	@FXML
	CheckBox mv;
	@FXML
	CheckBox pc;
	@FXML
	CheckBox rs;
	@FXML
	CheckBox tb;
	@FXML
	CheckBox tp;
	@FXML
	CheckBox ts;
	
	
	@FXML
	public void saveAppointment() {
		boolean err1 = false;
		List<String> services = new ArrayList<>();
		try {
			pdate.getValue().equals(null);
		} catch (NullPointerException e) {
			err1 = true;
			error.setText("Compila i campi obbligatori");
		}
		if(fperformingBarber.getText().isEmpty() || err1 || ftime.getText().isEmpty() || fbookingClient.getText().isEmpty() || fbookingBarber.getText().isEmpty()
			|| (!bp.isSelected() && !bs.isSelected() && !cl.isSelected() && !mb.isSelected() && !mv.isSelected() && !pc.isSelected() 
				&& !rs.isSelected() && !tb.isSelected() && !tp.isSelected() && !ts.isSelected())) {
			error.setText("Compila i campi obbligatori");
		} else {
			Stage s2 = (Stage) save.getScene().getWindow();
			Appointment a;
			Date d = Utils.buildDate(pdate.getValue().getDayOfMonth(), pdate.getValue().getMonthValue(), pdate.getValue().getYear()).get();
			
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
			
			a = new Appointment(getInteger(fperformingBarber.getText()), 
								Utils.dateToSqlDate(d),
								Time.valueOf(ftime.getText()), 
								getInteger(fbookingClient.getText()), 
								getInteger(fperformingClient.getText()),
								getInteger(fbookingBarber.getText()),
								null,
								null);
			aTable.save(a, services);
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
