package model;

import java.util.Objects;

public class Client {
	private Integer id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String mail;
	private Long phone;
	
	public Client(final Integer id, final String firstName, final String lastName, final String address, 
			final String city, final String mail, final Long phone) {
        this.id = Objects.requireNonNull(id);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.phone = phone;
    }
	
	public Client(final String firstName, final String lastName, final String address, 
			final String city, final String mail, final Long phone) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.phone = phone;
    }
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getMail() {
		return mail;
	}

	public Long getPhone() {
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
