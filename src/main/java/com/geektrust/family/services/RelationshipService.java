package com.geektrust.family.services;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.models.RoyalFamily;

class RelationshipService {

	private NuclearRelationshipService nuclearRelationshipService;

	RelationshipService(RoyalFamily royalFamily) {
		nuclearRelationshipService = new NuclearRelationshipService(royalFamily);
	}

	List<String> getPaternalUnclesOf(String name) throws IdentityException {
		String father = nuclearRelationshipService.getFatherOf(name);
		if(father != null)
			return nuclearRelationshipService.getBrothersOf(father);
		return null;
	}

	List<String> getPaternalAuntsOf(String name) throws IdentityException {
		String father = nuclearRelationshipService.getFatherOf(name);
		if(father != null)
			return nuclearRelationshipService.getSistersOf(father);
		return null;
	}

	List<String> getMaternalUnclesOf(String name) throws IdentityException {
		String mother = nuclearRelationshipService.getMotherOf(name);
		if(mother != null)
			return nuclearRelationshipService.getBrothersOf(mother);
		return null;
	}

	List<String> getMaternalAuntsOf(String name) throws IdentityException {
		String mother = nuclearRelationshipService.getMotherOf(name);
		if(mother != null)
			return nuclearRelationshipService.getSistersOf(mother);
		return null;
	}

	List<String> getSistersInLawOf(String name) throws IdentityException {
		String spouse = nuclearRelationshipService.getSpouseOf(name);
		List<String> sistersInLaws = new ArrayList<>();
		if(spouse != null)
			sistersInLaws = nuclearRelationshipService.getSistersOf(spouse);

		List<String> brothers = nuclearRelationshipService.getBrothersOf(name);
		for (String brother : brothers)
			sistersInLaws.add(nuclearRelationshipService.getSpouseOf(brother));
		return sistersInLaws;
	}

	List<String> getBrothersInLawOf(String name) throws IdentityException {
		String spouse = nuclearRelationshipService.getSpouseOf(name);
		List<String> brothersInLaws = new ArrayList<>();
		if(spouse != null)
			brothersInLaws = nuclearRelationshipService.getBrothersOf(spouse);

		List<String> sisters = nuclearRelationshipService.getSistersOf(name);
		for (String sister : sisters)
			brothersInLaws.add(nuclearRelationshipService.getSpouseOf(sister));
		return brothersInLaws;
	}

	List<String> getSonsOf(String name) throws IdentityException {
		List<String> sons = new ArrayList<>();
		List<String> children = nuclearRelationshipService.getChildrenOf(name);
		for (String child : children) {
			if (nuclearRelationshipService.hasMaleMember(child))
				sons.add(child);
		}
		return sons;
	}

	List<String> getDaughtersOf(String name) throws IdentityException {
		List<String> daughters = new ArrayList<>();
		List<String> children = nuclearRelationshipService.getChildrenOf(name);
		for (String child : children) {
			if (nuclearRelationshipService.hasMaleMember(child))
				daughters.add(child);
		}
		return daughters;
	}

	List<String> getSiblingsOf(String name) throws IdentityException {
		return nuclearRelationshipService.getSiblingsOf(name);
	}

}
