package model;

import java.util.Objects;

public class Fidelity {
	private Integer code;
	private Integer balance;
	private Integer clientId;
	
	public Fidelity(Integer code, Integer balance, Integer clientId) {
		this.code = Objects.requireNonNull(code);
		this.balance = Objects.requireNonNull(balance);
		this.clientId = Objects.requireNonNull(clientId);
	}

	public Integer getCode() {
		return code;
	}

	public Integer getBalance() {
		return balance;
	}

	public Integer getClientId() {
		return clientId;
	}

	@Override
	public String toString() {
		return "Fidelity [code=" + code + ", balance=" + balance + ", clientId=" + clientId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fidelity other = (Fidelity) obj;
		return Objects.equals(code, other.code);
	}
}
