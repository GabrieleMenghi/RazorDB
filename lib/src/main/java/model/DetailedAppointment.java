package model;

import java.sql.Date;
import java.sql.Time;

public class DetailedAppointment {
	
	private Date date;
	private Time time;
	private Integer idClient;
	private String clientFirstName;
	private String clientLastName;
	
	public DetailedAppointment(Date date, Time time, Integer idClient, String clientFirstName, String clientLastName) {
		this.date = date;
		this.time = time;
		this.idClient = idClient;
		this.clientFirstName = clientFirstName;
		this.clientLastName = clientLastName;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}	
}
