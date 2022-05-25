package model;

import java.util.Objects;

public class Service {
	private String name;
	private String description;
	private Float price;
	private Integer duration;
	private Integer requests1;
	private Integer requests2;
	private Integer requests3;
	private Integer requests4;
	
	public Service(String name, String description, Float price, Integer duration, Integer requests1, Integer requests2,
					Integer requests3, Integer requests4) {
		this.name = Objects.requireNonNull(name);
		this.description = Objects.requireNonNull(description);
		this.price = Objects.requireNonNull(price);
		this.duration = Objects.requireNonNull(duration);
		this.requests1 = requests1;
		this.requests2 = requests2;
		this.requests3 = requests3;
		this.requests4 = requests4;		
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getDuration() {
		return duration;
	}

	public Integer getRequests1() {
		return requests1;
	}

	public Integer getRequests2() {
		return requests2;
	}

	public Integer getRequests3() {
		return requests3;
	}

	public Integer getRequests4() {
		return requests4;
	}
}
