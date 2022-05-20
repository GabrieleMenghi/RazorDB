package model;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

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
	
	
}
