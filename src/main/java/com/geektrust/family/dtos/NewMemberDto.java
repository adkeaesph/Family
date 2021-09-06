package com.geektrust.family.dtos;

import com.geektrust.family.models.Gender;

public class NewMemberDto {

	private String name;
	private Gender gender;

	public NewMemberDto() {
	}

	public NewMemberDto(String name, Gender gender) {
		this();
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
