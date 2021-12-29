package com.geektrust.family.services;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.RoyalFamily;

public class RelationshipServiceTest {
	
	private RelationshipService relationshipService;
	
	private RoyalFamily getRoyalFamily() throws IdentityException, GenderException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");

		InitiationService initiationService = new InitiationService(royalPair);
		return initiationService.initiateFamilyTree();
	}

	@Before
	public void initiateOperationsService() throws IdentityException, GenderException, MarriageException {
		relationshipService = new RelationshipService(getRoyalFamily());
	}
	
	@Test
	public void successfulGetPaternalUnclesTest() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
		expectedUnclesList.add("Ish");
		expectedUnclesList.add("Aras");
		expectedUnclesList.add("Vich");
		Assert.assertEquals(expectedUnclesList, relationshipService.getPaternalUnclesOf("Dritha"));
	}
	
	@Test
	public void getPaternalUnclesTestWhenNoUnclesAreThere() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
		Assert.assertEquals(expectedUnclesList, relationshipService.getPaternalUnclesOf("Chit"));
	}
	
	@Test
	public void getPaternalUnclesTestWhenNoFatherIsThere() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
	    Assert.assertEquals(expectedUnclesList, relationshipService.getPaternalUnclesOf("Amba"));
	}
	
	@Test
	public void successfulGetPaternalAuntsTest() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
		expectedAuntsList.add("Satya");
		Assert.assertEquals(expectedAuntsList, relationshipService.getPaternalAuntsOf("Dritha"));
	}
	
	@Test
	public void getPaternalAuntsTestWhenNoAuntsAreThere() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
		Assert.assertEquals(expectedAuntsList, relationshipService.getPaternalAuntsOf("Chit"));
	}
	
	@Test
	public void getPaternalAuntsTestWhenNoFatherIsThere() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
	    Assert.assertEquals(expectedAuntsList, relationshipService.getPaternalAuntsOf("Amba"));
	}
	
	@Test
	public void successfulGetMaternalUnclesTest() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
		expectedUnclesList.add("Ish");
		expectedUnclesList.add("Aras");
		expectedUnclesList.add("Vich");
		expectedUnclesList.add("Chit");
		Assert.assertEquals(expectedUnclesList, relationshipService.getMaternalUnclesOf("Asva"));
	}
	
	@Test
	public void getMaternalUnclesTestWhenNoUnclesAreThere() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
		Assert.assertEquals(expectedUnclesList, relationshipService.getMaternalUnclesOf("Chit"));
	}
	
	@Test
	public void getMaternalUnclesTestWhenNoMotherIsThere() throws IdentityException {
		Set<String> expectedUnclesList = new HashSet<>();
	    Assert.assertEquals(expectedUnclesList, relationshipService.getMaternalUnclesOf("Amba"));
	}
	
	@Test
	public void successfulGetMaternalAuntsTest() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
		expectedAuntsList.add("Tritha");
		Assert.assertEquals(expectedAuntsList, relationshipService.getMaternalAuntsOf("Yodhan"));
	}
	
	@Test
	public void getMaternalAuntsTestWhenNoAuntsAreThere() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
		Assert.assertEquals(expectedAuntsList, relationshipService.getMaternalAuntsOf("Chit"));
	}
	
	@Test
	public void getMaternalAuntsTestWhenNoMotherIsThere() throws IdentityException {
		Set<String> expectedAuntsList = new HashSet<>();
	    Assert.assertEquals(expectedAuntsList, relationshipService.getMaternalAuntsOf("Amba"));
	}
	
	@Test
	public void successfulGetSistersInLawTest() throws IdentityException {
		Set<String> sistersInLaw = new HashSet<>();
		sistersInLaw.add("Amba");
		sistersInLaw.add("Lika");
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Aras"));
		
		sistersInLaw = new HashSet<>();
		sistersInLaw.add("Satya");
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Amba"));
	}
	
	@Test
	public void getSistersInLawWhenNoSpouseIsThereTest() throws IdentityException {
		Set<String> sistersInLaw = new HashSet<>();
		sistersInLaw.add("Amba");
		sistersInLaw.add("Lika");
		sistersInLaw.add("Chitra");
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Ish"));
	}
	
	@Test
	public void getSistersInLawWhenSpouseHasNoSistersTest() throws IdentityException {
		Set<String> sistersInLaw = new HashSet<>();
		sistersInLaw.add("Lika");
		sistersInLaw.add("Chitra");
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Chit"));
	}
	
	@Test
	public void getSistersInLawWhenNoBrotherIsThereTest() throws IdentityException {
		Set<String> sistersInLaw = new HashSet<>();
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Vila"));
	}
	
	@Test
	public void getSistersInLawWhenBrothersHaveNoSpousesTest() throws IdentityException {
		Set<String> sistersInLaw = new HashSet<>();
		Assert.assertEquals(sistersInLaw, relationshipService.getSistersInLawOf("Dritha"));
	}
	
	@Test
	public void successfulGetBrothersInLawTest() throws IdentityException {
		Set<String> brothersInLaw = new HashSet<>();
		brothersInLaw.add("Vyan");
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Chit"));
		
		brothersInLaw = new HashSet<>();
		brothersInLaw.add("Ish");
		brothersInLaw.add("Aras");
		brothersInLaw.add("Vich");
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Amba"));
	}
	
	@Test
	public void getBrothersInLawWhenNoSpouseIsThereTest() throws IdentityException {
		Set<String> brothersInLaw = new HashSet<>();
		brothersInLaw.add("Vyan");
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Ish"));
	}
	
	@Test
	public void getBrothersInLawWhenSpouseHasNoBrothersTest() throws IdentityException {
		Set<String> brothersInLaw = new HashSet<>();
		brothersInLaw.add("Vyan");
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Chit"));
	}
	
	@Test
	public void getBrothersInLawWhenNoSisterIsThereTest() throws IdentityException {
		Set<String> brothersInLaw = new HashSet<>();
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Satya"));
	}
	
	@Test
	public void getBrothersInLawWhenSistersHaveNoSpousesTest() throws IdentityException {
		Set<String> brothersInLaw = new HashSet<>();
		Assert.assertEquals(brothersInLaw, relationshipService.getBrothersInLawOf("Vila"));
	}
	
	@Test
	public void successfulGetSonsTest() throws IdentityException {
		Set<String> sons = new HashSet<>();
		sons.add("Chit");
		sons.add("Ish");
		sons.add("Vich");
		sons.add("Aras");
		Assert.assertEquals(sons, relationshipService.getSonsOf("King Shan"));
	}
	
	@Test
	public void getSonsWhenNoSpouseIsThereTest() throws IdentityException {
		Set<String> sons = new HashSet<>();
		Assert.assertEquals(sons, relationshipService.getSonsOf("Ish"));
	}
	
	@Test
	public void successfulGetDaughtersTest() throws IdentityException {
		Set<String> daughters = new HashSet<>();
		daughters.add("Satya");
		Assert.assertEquals(daughters, relationshipService.getDaughtersOf("Queen Anga"));
	}
	
	@Test
	public void getDaughtersWhenNoSpouseIsThereTest() throws IdentityException {
		Set<String> daughters = new HashSet<>();
		Assert.assertEquals(daughters, relationshipService.getDaughtersOf("Ish"));
	}
	
	@Test
	public void successfulGetSiblingsTest() throws IdentityException {
		Set<String> siblings = new HashSet<>();
		siblings.add("Ish");
		siblings.add("Vich");
		siblings.add("Aras");
		siblings.add("Satya");
		Assert.assertEquals(siblings, relationshipService.getSiblingsOf("Chit"));
	}
	
	@Test
	public void getSiblingsWhenYouAreAnOnlyChildTest() throws IdentityException {
		Set<String> siblings = new HashSet<>();
		Assert.assertEquals(siblings, relationshipService.getSiblingsOf("Yodhan"));
	}
}