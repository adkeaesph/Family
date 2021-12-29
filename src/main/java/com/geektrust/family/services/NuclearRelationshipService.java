package com.geektrust.family.services;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.models.Biodata;
import com.geektrust.family.models.RoyalFamily;

class NuclearRelationshipService {

	private RoyalFamily royalFamily;

	NuclearRelationshipService(RoyalFamily royalFamily) {
		this.royalFamily = royalFamily;
	}

	String getFatherOf(String name) throws IdentityException {
		String mother = getMotherOf(name);
		if(mother == null)
			return null;
		return getSpouseOf(mother);
	}

	String getMotherOf(String name) throws IdentityException {
		if (!royalFamily.hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");

		Biodata biodata = royalFamily.getBiodataOf(name);
		return biodata.getMothersName();
	}

	Set<String> getSiblingsOf(String name) throws IdentityException {
		if (!royalFamily.hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");

		Set<String> siblings;
		String mother = getMotherOf(name);
		if(mother != null) {
			siblings = getChildrenOf(mother);
			siblings.remove(name);
		} else {
			siblings = new LinkedHashSet<>();
		}
		return siblings;
	}

	Set<String> getBrothersOf(String name) throws IdentityException {
		if (!royalFamily.hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");

		Set<String> siblings = getSiblingsOf(name);
		Set<String> brothers = new LinkedHashSet<>();
		for (String sibling : siblings) {
			if (hasMaleMember(sibling))
				brothers.add(sibling);
		}
		return brothers;
	}

	Set<String> getSistersOf(String name) throws IdentityException {
		if (!royalFamily.hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");

		Set<String> siblings = getSiblingsOf(name);
		Set<String> sisters = new LinkedHashSet<>();
		for (String sibling : siblings) {
			if (hasFemaleMember(sibling))
				sisters.add(sibling);
		}
		return sisters;
	}

	Set<String> getChildrenOf(String name) throws IdentityException {
		if (royalFamily.hasMaleMember(name))
			name = getSpouseOf(name);

		Set<String> children = new LinkedHashSet<>();
		Iterator<Map.Entry<String, Biodata>> it = royalFamily.getIterator();
		while (it.hasNext()) {
			Map.Entry<String, Biodata> currentMember = it.next();
			String currentMemberName = currentMember.getKey();
			Biodata currentMemberBiodata = currentMember.getValue();
			String currentMembersMother = currentMemberBiodata.getMothersName();
			if (currentMembersMother != null && currentMembersMother.equals(name))
				children.add(currentMemberName);

		}
		return children;
	}

	String getSpouseOf(String name) throws IdentityException {
		if (!royalFamily.hasMemberWithName(name))
			throw new IdentityException("PERSON_NOT_FOUND");
		return royalFamily.getBiodataOf(name).getSpousesName();
	}

	boolean hasMaleMember(String name) throws IdentityException {
		return royalFamily.hasMaleMember(name);
	}

	boolean hasFemaleMember(String name) throws IdentityException {
		return royalFamily.hasFemaleMember(name);
	}

}
