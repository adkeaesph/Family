package com.geektrust.family;

import java.io.IOException;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.RoyalFamily;
import com.geektrust.family.services.InitiationService;
import com.geektrust.family.services.OutputService;

public class FamilyApplication {

	public static void main(String[] args) throws Exception {
		// 1st generation
		runFamilyApplication();

		//include in test cases
		//---
//		royalFamily.display();
//		System.out.println(royalFamily.getFamilyMembers().size());
		
//		System.out.println("Siblings of Chit : ");
//		System.out.println(operationsService.getRelationship("Chit", "Siblings"));
//		
//		System.out.println("Sons of Chit : ");
//		System.out.println(operationsService.getRelationship("Chit", "Son"));
//		
//		System.out.println("Daughters of Chit : ");
//		System.out.println(operationsService.getRelationship("Chit", "Daughter"));
//		
//		System.out.println("Paternal uncles of Dritha : ");
//		System.out.println(operationsService.getRelationship("Dritha", "Paternal-Uncle"));
//		
//		System.out.println("Paternal aunts of Dritha : ");
//		System.out.println(operationsService.getRelationship("Dritha", "Paternal-Aunt"));
//		
//		System.out.println("Maternal uncles of Vyas : ");
//		System.out.println(operationsService.getRelationship("Vyas", "Maternal-Uncle"));
//		
//		System.out.println("Maternal aunts of Vyas : ");
//		System.out.println(operationsService.getRelationship("Vyas", "Maternal-Aunt"));
	}

	private static void runFamilyApplication() throws IdentityException, GenderException, IOException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");
		
		InitiationService initiationService = new InitiationService(royalPair);
		RoyalFamily royalFamily = initiationService.initiateFamilyTree();
		
		OutputService outputService = new OutputService(royalFamily);
		outputService.readFileAndProduceResult(getFilePath());
	}
	
	private static String getFilePath() {
		return System.getProperty("user.dir")+"/src/main/resources/inputfiles/inputFile0.txt";
	}

}
