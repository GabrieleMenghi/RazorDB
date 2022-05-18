package model;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

public class Barber {
	private int id;
	private String firstName;
	private String lastName;
	private String cf;
	private Date birthDate;
	private String type;
	private Optional<Long> piva;
	
	public Barber(int id, String firstName, String lastName, String cf, Date birthDate, String type,
			Optional<Long> piva) {
		this.id = Objects.requireNonNull(id);
		this.firstName = Objects.requireNonNull(firstName);
		this.lastName = Objects.requireNonNull(lastName);
		this.cf = Objects.requireNonNull(cf);
		this.birthDate = Objects.requireNonNull(birthDate);
		this.type = Objects.requireNonNull(type);
		this.piva = piva;
	}

	public int getId() {
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

	public long getPiva() {
		return piva.orElse(null);
	}
	
	
}
