package model;

import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.Time;
import java.util.Objects;
import java.util.Optional;

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
		this.idPerformingBarber = idPerformingBarber;
		this.date = date;
		this.time = time;
		this.idBookingClient = idBookingClient;
		this.idPerformingClient = idPerformingClient;
		this.idBookingBarber = idBookingBarber;
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
}
