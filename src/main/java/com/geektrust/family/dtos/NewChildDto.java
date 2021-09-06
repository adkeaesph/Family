package com.geektrust.family.dtos;

import com.geektrust.family.models.Gender;

public class NewChildDto extends NewMemberDto {

	private String mothersName;
		
	public NewChildDto() {
		super();
	}

	public NewChildDto(String mothersName, String childsName, Gender childsGender) {
		super(childsName, childsGender);
		this.mothersName = mothersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public String getChildsName() {
		return super.getName();
	}

	public Gender getChildsGender() {
		return super.getGender();
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public void setChildsName(String childsName) {
		super.setName(childsName);
	}

	public void setChildsGender(Gender childsGender) {
		super.setGender(childsGender);
	}

	@Override
	public String toString() {
		return "NewChildDto [mothersName=" + getMothersName() + ", childsName=" + getChildsName() + ", childsGender="
				+ getChildsGender() + "]";
	}
	
}
