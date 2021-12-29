package com.geektrust.family.services;

import java.util.LinkedHashSet;
import java.util.Set;

import com.geektrust.family.dtos.NameRelationshipPair;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.models.RoyalFamily;

class OperationsService {

	private CeremonialService ceremonialService;
	private RelationshipService relationshipService;

	OperationsService(RoyalFamily royalFamily) {
		ceremonialService = new CeremonialService(royalFamily);
		relationshipService = new RelationshipService(royalFamily);
	}

	String addChild(NewChildDto newChildDto) {
		try {
			ceremonialService.addMember(newChildDto);
			return "CHILD_ADDITION_SUCCEEDED";
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	String addMember(NewMemberDto newMemberDto) {
		try {
			ceremonialService.addMember(newMemberDto);
			return "MEMBER_ADDITION_SUCCEEDED";
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	String conductMarriage(String spouse1, String spouse2) {
		try {
			ceremonialService.conductMarriage(spouse1, spouse2);
			return "MARRIAGE_SUCCEEDED";
		} catch (Exception exception) {
			return exception.getMessage();
		}
	}

	/*
	 * Paternal-Uncle, Maternal-Uncle, Paternal-Aunt, Maternal-Aunt, Sister-In-Law,
	 * Brother-In-Law, Son, Daughter, Siblings
	 */
	String getRelationship(NameRelationshipPair nameRelationshipPair) {
		return getRelatives(nameRelationshipPair);
	}

	private String getRelatives(NameRelationshipPair nameRelationshipPair) {
		String name = nameRelationshipPair.getName();
		String relationship = nameRelationshipPair.getRelationship();
		Set<String> members = new LinkedHashSet<>();

		try {
			switch (relationship) {
			case "Paternal-Uncle":
				members = relationshipService.getPaternalUnclesOf(name);
				break;

			case "Paternal-Aunt":
				members = relationshipService.getPaternalAuntsOf(name);
				break;

			case "Maternal-Uncle":
				members = relationshipService.getMaternalUnclesOf(name);
				break;

			case "Maternal-Aunt":
				members = relationshipService.getMaternalAuntsOf(name);
				break;

			case "Sister-In-Law":
				members = relationshipService.getSistersInLawOf(name);
				break;

			case "Brother-In-Law":
				members = relationshipService.getBrothersInLawOf(name);
				break;

			case "Son":
				members = relationshipService.getSonsOf(name);
				break;

			case "Daughter":
				members = relationshipService.getDaughtersOf(name);
				break;

			case "Siblings":
				members = relationshipService.getSiblingsOf(name);
				break;

			default:
				throw new Exception("No functionality implemented yet for given relationship!!!");
			}
		} catch (Exception exception) {
			return exception.getMessage();
		}

		if (members == null || members.isEmpty())
			return "NONE";

		String result = "";
		for (String member : members)
			result += member + " ";
		result = UtilService.removeLeadingAndTrailingSpacesFromString(result);
		return result;
	}

}
