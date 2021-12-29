package com.geektrust.family.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.Gender;
import com.geektrust.family.models.RoyalFamily;

public class CeremonialServiceTest {
	
	private CeremonialService ceremonialService;

	private RoyalFamily getRoyalFamily() throws IdentityException, GenderException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");

		InitiationService initiationService = new InitiationService(royalPair);
		return initiationService.initiateFamilyTree();
	}

	@Before
	public void initiateOperationsService() throws IdentityException, GenderException, MarriageException {
		ceremonialService = new CeremonialService(getRoyalFamily());
	}
	
	@Test
	public void successfulMemberAdditionTest() throws IdentityException, GenderException {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Neo");
		newMemberDto.setGender(Gender.MALE);
		Assert.assertEquals(true, ceremonialService.addMember(newMemberDto)); 
	}
	
	@Test
	public void memberAdditionFailureWhenMemberAlreadyExistsTest() throws GenderException {
		IdentityException exception = Assert.assertThrows(IdentityException.class, () -> {
			NewMemberDto newMemberDto = new NewMemberDto();
			newMemberDto.setName("Chit");
			newMemberDto.setGender(Gender.MALE);
			ceremonialService.addMember(newMemberDto);
	    });

	    Assert.assertEquals("Member with the given name already exists!", exception.getMessage());
	}
	
	@Test
	public void memberAdditionFailureWhenGenderIsNull() throws IdentityException {
		GenderException exception = Assert.assertThrows(GenderException.class, () -> {
			NewMemberDto newMemberDto = new NewMemberDto();
			newMemberDto.setName("Neo");
			newMemberDto.setGender(null);
			ceremonialService.addMember(newMemberDto);
	    });

	    Assert.assertEquals("Gender cannot be null!", exception.getMessage());
	}
	
	@Test
	public void successfulChildAdditionTest() throws IdentityException, GenderException {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setName("Neo");
		newChildDto.setGender(Gender.MALE);
		newChildDto.setMothersName("Amba");
		Assert.assertEquals(true, ceremonialService.addMember(newChildDto)); 
	}
	
	@Test
	public void childAdditionFailureWhenChildAlreadyExistsTest() throws GenderException {
		IdentityException exception = Assert.assertThrows(IdentityException.class, () -> {
			NewChildDto newChildDto = new NewChildDto();
			newChildDto.setName("Chit");
			newChildDto.setGender(Gender.MALE);
			newChildDto.setMothersName("Amba");
			ceremonialService.addMember(newChildDto);
	    });

	    Assert.assertEquals("CHILD_ADDITION_FAILED", exception.getMessage());
	}
	
	@Test
	public void childAdditionFailureWhenMotherDoesNotExistTest() throws GenderException {
		IdentityException exception = Assert.assertThrows(IdentityException.class, () -> {
			NewChildDto newChildDto = new NewChildDto();
			newChildDto.setName("Neo");
			newChildDto.setGender(Gender.MALE);
			newChildDto.setMothersName("Meera");
			ceremonialService.addMember(newChildDto);
	    });

	    Assert.assertEquals("PERSON_NOT_FOUND", exception.getMessage());
	}
	
	@Test
	public void childAdditionFailureWhenMotherIsNotFemaleTest() throws IdentityException {
		GenderException exception = Assert.assertThrows(GenderException.class, () -> {
			NewChildDto newChildDto = new NewChildDto();
			newChildDto.setName("Neo");
			newChildDto.setGender(Gender.MALE);
			newChildDto.setMothersName("Chit");
			ceremonialService.addMember(newChildDto);
	    });

	    Assert.assertEquals("CHILD_ADDITION_FAILED", exception.getMessage());
	}
	
	@Test
	public void successfulMarriageConductionTest() throws IdentityException, GenderException, MarriageException {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Lara");
		newMemberDto.setGender(Gender.FEMALE);
		ceremonialService.addMember(newMemberDto);
		Assert.assertEquals(true, ceremonialService.conductMarriage("Vasa", "Lara"));
	}
	
	@Test
	public void marriageConductionFailureWhenPersonIsNotFound() {
		IdentityException exception = Assert.assertThrows(IdentityException.class, () -> {
			ceremonialService.conductMarriage("Vasa", "Nidhi");
	    });

	    Assert.assertEquals("PERSON_NOT_FOUND", exception.getMessage());
	}
	
	@Test
	public void marriageConductionFailureWhenCoupleIsOfSameGender() {
		GenderException exception = Assert.assertThrows(GenderException.class, () -> {
			NewMemberDto newMemberDto = new NewMemberDto();
			newMemberDto.setName("Naresh");
			newMemberDto.setGender(Gender.MALE);
			ceremonialService.addMember(newMemberDto);
			ceremonialService.conductMarriage("Vasa", "Naresh");
	    });
		
		Assert.assertEquals("Only members of opposite gender can marry!", exception.getMessage());
	}
	
	@Test
	public void marriageConductionFailureWhenOnePersonIsAlreadyMarried() {
		MarriageException exception = Assert.assertThrows(MarriageException.class, () -> {
			NewMemberDto newMemberDto = new NewMemberDto();
			newMemberDto.setName("Manisha");
			newMemberDto.setGender(Gender.FEMALE);
			ceremonialService.addMember(newMemberDto);
			ceremonialService.conductMarriage("Chit", "Manisha");
	    });
		
		Assert.assertEquals("One or both of the people are already married!", exception.getMessage());
	}
	
	@Test
	public void marriageConductionFailureWhenTwoPersonsAreAlreadyMarried() {
		MarriageException exception = Assert.assertThrows(MarriageException.class, () -> {
			ceremonialService.conductMarriage("Chit", "Amba");
	    });
		Assert.assertEquals("One or both of the people are already married!", exception.getMessage());
	}
}
