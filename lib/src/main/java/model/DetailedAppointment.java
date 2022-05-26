package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class DetailedAppointment {
	
	private Date date;
	private Time time;
	private Integer idBarber;
	private Integer idClient;
	private String clientFirstName;
	private String clientLastName;
	
	public DetailedAppointment(Date date, Time time, Integer idPerformingBarber, Integer idClient, String clientFirstName, String clientLastName) {
		this.date = Objects.requireNonNull(date);
		this.time = Objects.requireNonNull(time);
		this.idBarber = Objects.requireNonNull(idPerformingBarber);
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
	
	public Integer getIdBarber() {
		return idBarber;
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
		return "DetailedAppointment [date=" + date + ", time=" + time + ", idPerformingBarber=" + idBarber
				+ ", idClient=" + idClient + ", clientFirstName=" + clientFirstName + ", clientLastName="
				+ clientLastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, idBarber, time);
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
		return Objects.equals(date, other.date) && Objects.equals(idBarber, other.idBarber)
				&& Objects.equals(time, other.time);
	}
}
