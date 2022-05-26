package model;

import java.sql.Date;
import java.util.Objects;

public class Barber {
	private Integer id;
	private String firstName;
	private String lastName;
	private String cf;
	private Date birthDate;
	private String type;
	private Long piva;
	
	public Barber(Integer id, String firstName, String lastName, String cf, Date birthDate, String type,
			Long piva) {
		this.id = Objects.requireNonNull(id);
		this.firstName = Objects.requireNonNull(firstName);
		this.lastName = Objects.requireNonNull(lastName);
		this.cf = Objects.requireNonNull(cf);
		this.birthDate = Objects.requireNonNull(birthDate);
		this.type = Objects.requireNonNull(type);
		this.piva = piva;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public String getCf() {
		return cf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getType() {
		return type;
	}

	public Long getPiva() {
		return piva;
	}

	@Override
	public String toString() {
		return "Barber [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", cf=" + cf
				+ ", birthDate=" + birthDate + ", type=" + type + ", piva=" + piva + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barber other = (Barber) obj;
		return Objects.equals(id, other.id);
	}
}
