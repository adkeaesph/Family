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

public class NuclearRelationshipServiceTest {
	
	private NuclearRelationshipService nuclearRelationshipService;

	private RoyalFamily getRoyalFamily() throws IdentityException, GenderException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");

		InitiationService initiationService = new InitiationService(royalPair);
		return initiationService.initiateFamilyTree();
	}

	@Before
	public void initiateOperationsService() throws IdentityException, GenderException, MarriageException {
		nuclearRelationshipService = new NuclearRelationshipService(getRoyalFamily());
	}
	
	@Test
	public void successfulGetFatherTest() throws IdentityException {
		Assert.assertEquals("King Shan", nuclearRelationshipService.getFatherOf("Chit"));
	}
	
	@Test
	public void getFatherWhenFatherNotKnownTest() throws IdentityException {
		Assert.assertEquals(null, nuclearRelationshipService.getFatherOf("Amba"));
	}
	
	@Test
	public void successfulGetMotherTest() throws IdentityException {
		Assert.assertEquals("Queen Anga", nuclearRelationshipService.getMotherOf("Chit"));
	}
	
	@Test
	public void getMotherWhenMotherNotKnownTest() throws IdentityException {
		Assert.assertEquals(null, nuclearRelationshipService.getMotherOf("Amba"));
	}
	
	@Test
	public void successfulGetSiblingsTest() throws IdentityException {
		Set<String> siblings = new HashSet<>();
		siblings.add("Ish");
		siblings.add("Aras");
		siblings.add("Vich");
		siblings.add("Satya");
		Assert.assertEquals(siblings, nuclearRelationshipService.getSiblingsOf("Chit"));
	}
	
	@Test
	public void getSiblingsWhenMotherNotKnownTest() throws IdentityException {
		Set<String> siblings = new HashSet<>();
		Assert.assertEquals(siblings, nuclearRelationshipService.getSiblingsOf("Amba"));
	}
	
	@Test
	public void getSiblingsWhenOnlyChildTest() throws IdentityException {
		Set<String> siblings = new HashSet<>();
		Assert.assertEquals(siblings, nuclearRelationshipService.getSiblingsOf("Yodhan"));
	}
	
	@Test
	public void successfulGetBrothersTest() throws IdentityException {
		Set<String> brothers = new HashSet<>();
		brothers.add("Ish");
		brothers.add("Aras");
		brothers.add("Vich");
		Assert.assertEquals(brothers, nuclearRelationshipService.getBrothersOf("Chit"));
	}
	
	@Test
	public void getBrothersWhenMotherNotKnownTest() throws IdentityException {
		Set<String> brothers = new HashSet<>();
		Assert.assertEquals(brothers, nuclearRelationshipService.getBrothersOf("Amba"));
	}
	
	@Test
	public void getBrothersWhenNoBrothersTest() throws IdentityException {
		Set<String> brothers = new HashSet<>();
		Assert.assertEquals(brothers, nuclearRelationshipService.getBrothersOf("Chika"));
	}
	
	@Test
	public void getBrothersWhenOnlyChildTest() throws IdentityException {
		Set<String> brothers = new HashSet<>();
		Assert.assertEquals(brothers, nuclearRelationshipService.getBrothersOf("Yodhan"));
	}
	
	@Test
	public void successfulGetSistersTest() throws IdentityException {
		Set<String> sisters = new HashSet<>();
		sisters.add("Satya");
		Assert.assertEquals(sisters, nuclearRelationshipService.getSistersOf("Chit"));
	}
	
	@Test
	public void getSistersWhenMotherNotKnownTest() throws IdentityException {
		Set<String> sisters = new HashSet<>();
		Assert.assertEquals(sisters, nuclearRelationshipService.getSistersOf("Amba"));
	}
	
	@Test
	public void getSistersWhenNoSistersTest() throws IdentityException {
		Set<String> sisters = new HashSet<>();
		Assert.assertEquals(sisters, nuclearRelationshipService.getSistersOf("Vasa"));
	}
	
	@Test
	public void getSistersWhenOnlyChildTest() throws IdentityException {
		Set<String> sisters = new HashSet<>();
		Assert.assertEquals(sisters, nuclearRelationshipService.getSistersOf("Yodhan"));
	}
	
	@Test
	public void successfulGetChildrenForTheMotherTest() throws IdentityException {
		Set<String> children = new HashSet<>();
		children.add("Chit");
		children.add("Ish");
		children.add("Aras");
		children.add("Vich");
		children.add("Satya");
		Assert.assertEquals(children, nuclearRelationshipService.getChildrenOf("Queen Anga"));
	}
	
	@Test
	public void successfulGetChildrenForTheFatherTest() throws IdentityException {
		Set<String> children = new HashSet<>();
		children.add("Chit");
		children.add("Ish");
		children.add("Aras");
		children.add("Vich");
		children.add("Satya");
		Assert.assertEquals(children, nuclearRelationshipService.getChildrenOf("King Shan"));
	}
	
	@Test
	public void getChildrenForUnmarriedMaleTest() throws IdentityException {
		Set<String> children = new HashSet<>();
		Assert.assertEquals(children, nuclearRelationshipService.getChildrenOf("Ish"));
	}
	
	@Test
	public void getChildrenForUnmarriedFemaleTest() throws IdentityException {
		Set<String> children = new HashSet<>();
		Assert.assertEquals(children, nuclearRelationshipService.getChildrenOf("Chika"));
	}
	
	@Test
	public void successfulGetSpouseTest() throws IdentityException {
		Assert.assertEquals("Chit", nuclearRelationshipService.getSpouseOf("Amba"));
	}
	
	@Test
	public void getSpouseOfUnmarriedPersonTest() throws IdentityException {
		Assert.assertEquals(null, nuclearRelationshipService.getSpouseOf("Ish"));
	}
	
	@Test
	public void successfulHasMaleMemberTest() throws IdentityException {
		Assert.assertEquals(true, nuclearRelationshipService.hasMaleMember("Chit"));
	}
	
	@Test
	public void failedHasMaleMemberTest() throws IdentityException {
		Assert.assertEquals(false, nuclearRelationshipService.hasMaleMember("Amba"));
	}
	
	@Test
	public void successfulHasFemaleMemberTest() throws IdentityException {
		Assert.assertEquals(true, nuclearRelationshipService.hasFemaleMember("Amba"));
	}
	
	@Test
	public void failedHasFemaleMemberTest() throws IdentityException {
		Assert.assertEquals(false, nuclearRelationshipService.hasFemaleMember("Chit"));
	}
}
