package com.geektrust.family.services;

import java.util.ArrayList;
import java.util.List;

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
			System.out.println(exception.getMessage());
			return "MARRIAGE_FAILED";
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
		List<String> members = new ArrayList<>();
		if (relationship.equals("Paternal-Uncle")) {
			try {
				members = relationshipService.getPaternalUnclesOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Paternal-Aunt")) {
			try {
				members = relationshipService.getPaternalAuntsOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Maternal-Uncle")) {
			try {
				members = relationshipService.getMaternalUnclesOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Maternal-Aunt")) {
			try {
				members = relationshipService.getMaternalAuntsOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Sister-In-Law")) {
			try {
				members = relationshipService.getSistersInLawOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Brother-In-Law")) {
			try {
				members = relationshipService.getBrothersInLawOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Son")) {
			try {
				members = relationshipService.getSonsOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Daughter")) {
			try {
				members = relationshipService.getDaughtersOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		} else if (relationship.equals("Siblings")) {
			try {
				members = relationshipService.getSiblingsOf(name);
			} catch (Exception exception) {
				return exception.getMessage();
			}
		}

		if (members == null || members.isEmpty())
			return "NONE";

		String result = "";
		for (String member : members)
			result += member + " ";
		return result;
	}

}
