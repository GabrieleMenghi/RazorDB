package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DetailedAppointment {
	
	private Date date;
	private Time time;
	private Integer idClient;
	private String clientFirstName;
	private String clientLastName;
	
	public DetailedAppointment(Date date, Time time, Integer idClient, String clientFirstName, String clientLastName) {
		this.date = Objects.requireNonNull(date);
		this.time = Objects.requireNonNull(time);
		this.idClient = Objects.requireNonNull(idClient);
		this.clientFirstName = Objects.requireNonNull(clientFirstName);
		this.clientLastName = Objects.requireNonNull(clientLastName);
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

	@Override
	public String toString() {
		return "DetailedAppointment [date=" + date + ", time=" + time + ", idClient=" + idClient + ", clientFirstName="
				+ clientFirstName + ", clientLastName=" + clientLastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientFirstName, clientLastName, date, idClient, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailedAppointment other = (DetailedAppointment) obj;
		return Objects.equals(clientFirstName, other.clientFirstName)
				&& Objects.equals(clientLastName, other.clientLastName) && Objects.equals(date, other.date)
				&& Objects.equals(idClient, other.idClient) && Objects.equals(time, other.time);
	}
}
