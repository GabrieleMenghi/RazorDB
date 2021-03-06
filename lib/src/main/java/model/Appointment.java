package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Appointment {
	private Integer idPerformingBarber;
	private Date date;
	private Time time;
	private Integer idBookingClient;
	private Integer idPerformingClient;
	private Integer idBookingBarber;
	private Integer receiptNumber;
	private Date receiptDate;
	
	public Appointment(Integer idPerformingBarber, Date date, Time time, Integer idBookingClient, Integer idPerformingClient,
			Integer idBookingBarber, Integer receiptNumber, Date receiptDate) {
		this.idPerformingBarber = Objects.requireNonNull(idPerformingBarber);
		this.date = Objects.requireNonNull(date);
		this.time = Objects.requireNonNull(time);
		this.idBookingClient = idBookingClient;
		this.idPerformingClient = idPerformingClient;
		this.idBookingBarber = Objects.requireNonNull(idBookingBarber);
		this.receiptNumber = receiptNumber;
		this.receiptDate = receiptDate;
	}
	
	public Integer getIdPerformingBarber() {
		return idPerformingBarber;
	}
	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
	public Integer getIdBookingClient() {
		return idBookingClient;
	}
	public Integer getIdPerformingClient() {
		return idPerformingClient;
	}
	public Integer getIdBookingBarber() {
		return idBookingBarber;
	}
	public Integer getReceiptNumber() {
		return receiptNumber;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}

	@Override
	public String toString() {
		return "Appointment [idPerformingBarber=" + idPerformingBarber + ", date=" + date + ", time=" + time
				+ ", idBookingClient=" + idBookingClient + ", idPerformingClient=" + idPerformingClient
				+ ", idBookingBarber=" + idBookingBarber + ", receiptNumber=" + receiptNumber + ", receiptDate="
				+ receiptDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, idPerformingBarber, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(date, other.date) && Objects.equals(idPerformingBarber, other.idPerformingBarber)
				&& Objects.equals(time, other.time);
	}
}
