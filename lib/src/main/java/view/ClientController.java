package view;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ClientController implements Initializable{
	
	private Integer clientCode;
	
	public void setCode(final Integer code) {
		this.clientCode = code;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
}
