package com.geektrust.family.services;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.RoyalFamily;

public class OutputServiceTest {

	private OutputService outputService;

	private RoyalFamily getRoyalFamily() throws IdentityException, GenderException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");

		InitiationService initiationService = new InitiationService(royalPair);
		return initiationService.initiateFamilyTree();
	}

	@Before
	public void initiateOutputService() throws IdentityException, GenderException, MarriageException {
		outputService = new OutputService(getRoyalFamily());
	}

	@Test
	public void readFileAndProduceResultExceptionTest() throws IOException {
		Assert.assertEquals("Wrong file format!!!", outputService.readFileAndProduceResult(
				System.getProperty("user.dir") + "/src/main/resources/inputfiles/inputFile4.txt"));
	}

	@Test
	public void readFileAndProduceResultTest() throws IOException {
		String[] expectedOutput = { "CHILD_ADDITION_SUCCEEDED\nAria\nJnki Ahit", "PERSON_NOT_FOUND\nPERSON_NOT_FOUND",
				"CHILD_ADDITION_FAILED\nNONE", "Satvy Krpi" };
		String[] methodOutput = {
				outputService.readFileAndProduceResult(
						System.getProperty("user.dir") + "/src/main/resources/inputfiles/inputFile0.txt"),
				outputService.readFileAndProduceResult(
						System.getProperty("user.dir") + "/src/main/resources/inputfiles/inputFile1.txt"),
				outputService.readFileAndProduceResult(
						System.getProperty("user.dir") + "/src/main/resources/inputfiles/inputFile2.txt"),
				outputService.readFileAndProduceResult(
						System.getProperty("user.dir") + "/src/main/resources/inputfiles/inputFile3.txt") };
		Assert.assertArrayEquals(expectedOutput, methodOutput);
	}

}
