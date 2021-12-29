package com.geektrust.family;

import java.io.IOException;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.RoyalFamily;
import com.geektrust.family.services.InitiationService;
import com.geektrust.family.services.OutputService;

public class FamilyApplication {

	public static void main(String[] args) throws Exception {
		runFamilyApplication();
	}

	private static void runFamilyApplication() throws IdentityException, GenderException, IOException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");
		
		InitiationService initiationService = new InitiationService(royalPair);
		RoyalFamily royalFamily = initiationService.initiateFamilyTree();
		
		OutputService outputService = new OutputService(royalFamily);
		System.out.print(outputService.readFileAndProduceResult(getFilePath()));
	}
	
	private static String getFilePath() {
		return System.getProperty("user.dir")+"/src/main/resources/inputfiles/inputFile6.txt";
	}

}
