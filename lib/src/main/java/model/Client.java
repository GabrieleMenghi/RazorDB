package model;

import java.util.Objects;
import java.util.Optional;

public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private Optional<String> address;
	private Optional<String> city;
	private Optional<String> mail;
	private Optional<Long> phone;
	
	public Client(final int id, final String firstName, final String lastName, final Optional<String> address, 
			final Optional<String> city, final Optional<String> mail, final Optional<Long> phone) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.phone = phone;
    }
	
	/*public Client(final String firstName, final String lastName, final Optional<String> address, 
			final Optional<String> city, final Optional<String> mail, final Optional<Long> phone) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.phone = phone;
    }*/
	
	public int getId() {
		return this.id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Optional<String> getAddress() {
		return address;
	}

	public Optional<String> getCity() {
		return city;
	}

	public Optional<String> getMail() {
		return mail;
	}

	public Optional<Long> getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", mail=" + mail + ", phone=" + phone + "]";
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
		Client other = (Client) obj;
		return id == other.id;
	}	
}