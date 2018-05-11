package com.pi;

import org.apache.commons.lang3.RandomStringUtils;

public class Korisnik {
	private String name;
	private String surname;
	private String jmbg;
	private String accountNum;
	
	public Korisnik(String name, String surname, String jmbg) {
		super();
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
		this.accountNum = makeAcc();
	}

	private String makeAcc() {
		StringBuilder sb = new StringBuilder();
		sb.append("RS");
		sb.append(RandomStringUtils.randomNumeric(2));
		sb.append("-");
		sb.append("160-");
		sb.append(RandomStringUtils.randomNumeric(13));
		sb.append("-");
		sb.append(RandomStringUtils.randomNumeric(2));
		return sb.toString();
	}

	@Override
	public String toString() {
		return this.name + " " + this.surname + " " + "Broj racuna: " + this.accountNum;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getAccountNum() {
		return accountNum;
	}
	
	
	
}
