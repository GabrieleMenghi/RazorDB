package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class ClientAppointment {
	private Date date;
	private Time time;
	private String bookingBarber;
	private Integer receiptNumber;
	private Date receiptDate;
	
	public ClientAppointment(Date date, Time time, String bookingBarber, Integer receiptNumber, Date receiptDate) {
		this.date = Objects.requireNonNull(date);
		this.time = Objects.requireNonNull(time);
		this.bookingBarber = Objects.requireNonNull(bookingBarber);
		this.receiptNumber = receiptNumber;
		this.receiptDate = receiptDate;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public String getBookingBarber() {
		return bookingBarber;
	}

	public Integer getReceiptNumber() {
		return receiptNumber;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	@Override
	public String toString() {
		return "ClientAppointment [date=" + date + ", time=" + time + ", bookingBarber=" + bookingBarber
				+ ", receiptNumber=" + receiptNumber + ", receiptDate=" + receiptDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookingBarber, date, receiptDate, receiptNumber, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAppointment other = (ClientAppointment) obj;
		return Objects.equals(bookingBarber, other.bookingBarber) && Objects.equals(date, other.date)
				&& Objects.equals(receiptDate, other.receiptDate) && Objects.equals(receiptNumber, other.receiptNumber)
				&& Objects.equals(time, other.time);
	}
}
