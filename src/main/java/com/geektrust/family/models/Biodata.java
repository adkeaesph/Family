package com.geektrust.family.models;

public class Biodata {

	private Gender gender;
	private String mothersName;
	private String spousesName;
	
	public Biodata(Gender gender) {
		this.gender = gender;
	}
	public Biodata(Gender gender, String mothersName) {
		this(gender);
		this.mothersName = mothersName;
	}
	
	public Biodata(Gender gender, String mothersName, String spousesName) {
		this(gender, mothersName);
		this.spousesName = spousesName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getSpousesName() {
		return spousesName;
	}

	public void setSpousesName(String spousesName) {
		this.spousesName = spousesName;
	}
	
}
