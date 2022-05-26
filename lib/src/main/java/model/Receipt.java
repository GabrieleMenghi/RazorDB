package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Receipt {
	private int id;
	private Date date;
	private Time time;
	private float total;
	private int idBarber;
	private int idClient;
	
	public Receipt(int id, Date date, Time time, float total, int idBarber, int idClient) {
		this.id = Objects.requireNonNull(id);
		this.date = Objects.requireNonNull(date);
		this.time = Objects.requireNonNull(time);
		this.total = Objects.requireNonNull(total);
		this.idBarber = Objects.requireNonNull(idBarber);
		this.idClient = Objects.requireNonNull(idClient);
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public float getTotal() {
		return total;
	}

	public int getIdBarber() {
		return idBarber;
	}

	public int getIdClient() {
		return idClient;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", date=" + date + ", time=" + time + ", total=" + total + ", idBarber=" + idBarber
				+ ", idClient=" + idClient + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receipt other = (Receipt) obj;
		return Objects.equals(date, other.date) && id == other.id;
	}
}
