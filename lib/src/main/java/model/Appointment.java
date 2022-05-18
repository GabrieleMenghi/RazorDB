package model;

import java.sql.Time;
import java.util.Date;
import java.util.Optional;

public class Appointment {
	private int idPerformingBarber;
	private Date date;
	private Time time;
	private int idBookingClient;
	private Optional<Integer> idPerformingClient;
	private int idBookingBarber;
	private Optional<Integer> receiptNumber;
	private Optional<Date> receiptDate;
	
	public Appointment(int idPerformingBarber, Date date, Time time, int idBookingClient,
			Optional<Integer> idPerformingClient, int idBookingBarber, Optional<Integer> receiptNumber,
			Optional<Date> receiptDate) {
		this.idPerformingBarber = idPerformingBarber;
		this.date = date;
		this.time = time;
		this.idBookingClient = idBookingClient;
		this.idPerformingClient = idPerformingClient;
		this.idBookingBarber = idBookingBarber;
		this.receiptNumber = receiptNumber;
		this.receiptDate = receiptDate;
	}

	public int getIdPerformingBarber() {
		return idPerformingBarber;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public int getIdBookingClient() {
		return idBookingClient;
	}

	public int getIdPerformingClient() {
		return idPerformingClient.orElse(null);
	}

	public int getIdBookingBarber() {
		return idBookingBarber;
	}

	public int getReceiptNumber() {
		return receiptNumber.orElse(null);
	}

	public Date getReceiptDate() {
		return receiptDate.orElse(null);
	}
	
	
	
	
	
	
}
