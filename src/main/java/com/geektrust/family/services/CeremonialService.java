package com.geektrust.family.services;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.models.RoyalFamily;

class CeremonialService {

	private RoyalFamily royalFamily;

	CeremonialService(RoyalFamily royalFamily) {
		this.royalFamily = royalFamily;
	}

	boolean addMember(NewMemberDto newMemberDto) throws IdentityException, GenderException {
		if (royalFamily.hasMemberWithName(newMemberDto.getName()))
			throw new IdentityException("Member with the given name already exists!");

		if (newMemberDto.getGender() == null)
			throw new GenderException("Gender cannot be null!");

		royalFamily.add(newMemberDto);
		return true;

	}

	boolean addMember(NewChildDto newChildDto) throws IdentityException, GenderException {
		if (royalFamily.hasMemberWithName(newChildDto.getChildsName()))
			throw new IdentityException("CHILD_ADDITION_FAILED");

		String mothersName = newChildDto.getMothersName();
		if (!royalFamily.hasFemaleMember(mothersName))
			throw new GenderException("CHILD_ADDITION_FAILED");

		royalFamily.add(newChildDto);
		return true;

	}

	boolean conductMarriage(String spouseName1, String spouseName2)
			throws IdentityException, GenderException, MarriageException {
		if (royalFamily.getGenderOf(spouseName1).equals(royalFamily.getGenderOf(spouseName2)))
			throw new GenderException("Only members of opposite gender can marry!");

		if (royalFamily.isMemberMarried(spouseName1) || royalFamily.isMemberMarried(spouseName2))
			throw new MarriageException("One or both of the people are already married!");

		royalFamily.getBiodataOf(spouseName1).setSpousesName(spouseName2);
		royalFamily.getBiodataOf(spouseName2).setSpousesName(spouseName1);
		return true;
	}

	RoyalFamily getRoyalFamily() {
		return royalFamily;
	}
}
