package com.geektrust.family.services;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.models.RoyalFamily;

class RelationshipService {

	private NuclearRelationshipService nuclearRelationshipService;

	RelationshipService(RoyalFamily royalFamily) {
		nuclearRelationshipService = new NuclearRelationshipService(royalFamily);
	}

	Set<String> getPaternalUnclesOf(String name) throws IdentityException {
		String father = nuclearRelationshipService.getFatherOf(name);
		if (father != null)
			return nuclearRelationshipService.getBrothersOf(father);
		return new HashSet<String>();
	}

	Set<String> getPaternalAuntsOf(String name) throws IdentityException {
		String father = nuclearRelationshipService.getFatherOf(name);
		if (father != null)
			return nuclearRelationshipService.getSistersOf(father);
		return new HashSet<String>();
	}

	Set<String> getMaternalUnclesOf(String name) throws IdentityException {
		String mother = nuclearRelationshipService.getMotherOf(name);
		if (mother != null)
			return nuclearRelationshipService.getBrothersOf(mother);
		return new HashSet<String>();
	}

	Set<String> getMaternalAuntsOf(String name) throws IdentityException {
		String mother = nuclearRelationshipService.getMotherOf(name);
		if (mother != null)
			return nuclearRelationshipService.getSistersOf(mother);
		return new HashSet<String>();
	}

	Set<String> getSistersInLawOf(String name) throws IdentityException {
		String spouse = nuclearRelationshipService.getSpouseOf(name);
		Set<String> sistersInLaws = new LinkedHashSet<>();
		try {
			if (spouse != null)
				sistersInLaws = nuclearRelationshipService.getSistersOf(spouse);
		} catch (IdentityException exception) {
			sistersInLaws = new LinkedHashSet<>();
		}

		Set<String> brothers = nuclearRelationshipService.getBrothersOf(name);
		String sisterInLaw;
		for (String brother : brothers) {
			sisterInLaw = nuclearRelationshipService.getSpouseOf(brother);
			if (sisterInLaw != null)
				sistersInLaws.add(sisterInLaw);
		}
		return sistersInLaws;
	}

	Set<String> getBrothersInLawOf(String name) throws IdentityException {
		String spouse = nuclearRelationshipService.getSpouseOf(name);
		Set<String> brothersInLaws = new LinkedHashSet<>();
		try {
			if (spouse != null)
				brothersInLaws = nuclearRelationshipService.getBrothersOf(spouse);
		} catch (IdentityException exception) {
			brothersInLaws = new LinkedHashSet<>();
		}

		Set<String> sisters = nuclearRelationshipService.getSistersOf(name);
		String brotherInLaw;
		for (String sister : sisters) {
			brotherInLaw = nuclearRelationshipService.getSpouseOf(sister);
			if (brotherInLaw != null)
				brothersInLaws.add(brotherInLaw);
		}
		return brothersInLaws;
	}

	Set<String> getSonsOf(String name) throws IdentityException {
		Set<String> sons = new LinkedHashSet<>();
		Set<String> children = nuclearRelationshipService.getChildrenOf(name);
		for (String child : children) {
			if (nuclearRelationshipService.hasMaleMember(child))
				sons.add(child);
		}
		return sons;
	}

	Set<String> getDaughtersOf(String name) throws IdentityException {
		Set<String> daughters = new LinkedHashSet<>();
		Set<String> children = nuclearRelationshipService.getChildrenOf(name);
		for (String child : children) {
			if (nuclearRelationshipService.hasFemaleMember(child))
				daughters.add(child);
		}
		return daughters;
	}

	Set<String> getSiblingsOf(String name) throws IdentityException {
		return nuclearRelationshipService.getSiblingsOf(name);
	}

}
