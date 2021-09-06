package com.geektrust.family.models;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.dtos.RoyalPair;

public class RoyalFamily {

	private Map<String, Biodata> familyMembers;

	public RoyalFamily(RoyalPair royalPair) {
		String kingsName = royalPair.getKingsName();
		String queensName = royalPair.getQueensName();
		
		familyMembers = new LinkedHashMap<>();
		familyMembers.put(kingsName, new Biodata(Gender.MALE, null, queensName));
		familyMembers.put(queensName, new Biodata(Gender.FEMALE, null, kingsName));
	}
	
	public Map<String, Biodata> getFamilyMembers() {
		return familyMembers;
	}

	public int size() {
		return familyMembers.size();
	}

	public boolean hasMemberWithName(String name) {
		return familyMembers.containsKey(name);
	}
	
	public Biodata getBiodataOf(String name) {
		return familyMembers.get(name);
	}
	
	public Biodata add(NewMemberDto newMemberDto) {
		String name = newMemberDto.getName();
		Biodata biodata = new Biodata(newMemberDto.getGender());
		if(newMemberDto instanceof NewChildDto)
			biodata.setMothersName(((NewChildDto)newMemberDto).getMothersName());
		return familyMembers.put(name, biodata);
	}

	public boolean hasMaleMember(String name) throws IdentityException {
		if (!hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");
		return getGenderOf(name).equals(Gender.MALE);
	}
	
	public boolean hasFemaleMember(String name) throws IdentityException {
		if (!hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");
		return getGenderOf(name).equals(Gender.FEMALE);
	}
	
	public Iterator<Entry<String, Biodata>> getIterator(){
		return familyMembers.entrySet().iterator();
	}
	
	public Gender getGenderOf(String name) {
		return familyMembers.get(name).getGender();
	}
	
	

	public void display() {
		Iterator<Map.Entry<String, Biodata>> it = familyMembers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Biodata> currentMember = it.next();
			String name = currentMember.getKey();
			Biodata biodata = currentMember.getValue();
			System.out.println("name = " + name + ", gender = " + biodata.getGender() + ", mother = "
					+ biodata.getMothersName() + ", spouse = " + biodata.getSpousesName());
		}
	}
}
