package model;

import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.Time;
import java.util.Objects;
import java.util.Optional;

public class Appointment {
	private int idPerformingBarber;
	private Date date;
	private Time time;
	private int idBookingClient;
	private Integer idPerformingClient;
	private int idBookingBarber;
	private Integer receiptNumber;
	private Date receiptDate;
	
	public Appointment(int idPerformingBarber, Date date, Time time, int idBookingClient, Integer idPerformingClient,
			int idBookingBarber, Integer receiptNumber, Date receiptDate) {
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
	public Integer getIdPerformingClient() {
		return idPerformingClient;
	}
	public int getIdBookingBarber() {
		return idBookingBarber;
	}
	public Integer getReceiptNumber() {
		return receiptNumber;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}	
}
