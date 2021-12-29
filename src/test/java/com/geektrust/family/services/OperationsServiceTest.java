package com.geektrust.family.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.customexceptions.MarriageException;
import com.geektrust.family.dtos.NameRelationshipPair;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.Gender;
import com.geektrust.family.models.RoyalFamily;

public class OperationsServiceTest {

	private OperationsService operationsService;

	private RoyalFamily getRoyalFamily() throws IdentityException, GenderException, MarriageException {
		RoyalPair royalPair = new RoyalPair();
		royalPair.setKingsName("King Shan");
		royalPair.setQueensName("Queen Anga");

		InitiationService initiationService = new InitiationService(royalPair);
		return initiationService.initiateFamilyTree();
	}

	@Before
	public void initiateOperationsService() throws IdentityException, GenderException, MarriageException {
		operationsService = new OperationsService(getRoyalFamily());
	}

	@Test
	public void siblingsRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Siblings");
		Assert.assertEquals("Ish Vich Aras Satya", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void sonRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Son");
		Assert.assertEquals("Vritha", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void daughterRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Daughter");
		Assert.assertEquals("Dritha Tritha", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void paternalUncleRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Dritha");
		nameRelationshipPair.setRelationship("Paternal-Uncle");
		Assert.assertEquals("Ish Vich Aras", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void paternalAuntRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Dritha");
		nameRelationshipPair.setRelationship("Paternal-Aunt");
		Assert.assertEquals("Satya", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void maternalUncleRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Vyas");
		nameRelationshipPair.setRelationship("Maternal-Uncle");
		Assert.assertEquals("Chit Ish Vich Aras", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void maternalAuntRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Vyas");
		nameRelationshipPair.setRelationship("Maternal-Aunt");
		Assert.assertEquals("NONE", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void brotherInLawRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Brother-In-Law");
		Assert.assertEquals("Vyan", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void sisterInLawRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Sister-In-Law");
		Assert.assertEquals("Lika Chitra", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void invalidNameOfRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Chit");
		nameRelationshipPair.setRelationship("Random-Relation");
		Assert.assertEquals("No functionality implemented yet for given relationship!!!",
				operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void personNotAvailableForMentionedValidRelationshipTest() {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName("Amba");
		nameRelationshipPair.setRelationship("Maternal-Aunt");
		Assert.assertEquals("NONE", operationsService.getRelationship(nameRelationshipPair));
	}

	@Test
	public void successfulChildAdditionTest() {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setChildsName("Naina");
		newChildDto.setChildsGender(Gender.FEMALE);
		newChildDto.setMothersName("Amba");
		Assert.assertEquals("CHILD_ADDITION_SUCCEEDED", operationsService.addChild(newChildDto));
	}

	@Test
	public void childAdditionFailureWhenChildIsAlreadyPresentTest() {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setChildsName("Chit");
		newChildDto.setChildsGender(Gender.MALE);
		newChildDto.setMothersName("Amba");
		Assert.assertEquals("CHILD_ADDITION_FAILED", operationsService.addChild(newChildDto));
	}

	@Test
	public void childAdditionFailureWhenMotherIsNotFemale() {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setChildsName("Naina");
		newChildDto.setChildsGender(Gender.FEMALE);
		newChildDto.setMothersName("Chit");
		Assert.assertEquals("CHILD_ADDITION_FAILED", operationsService.addChild(newChildDto));
	}

	@Test
	public void childAdditionFailureWhenMotherIsNotFound() {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setChildsName("Naina");
		newChildDto.setChildsGender(Gender.FEMALE);
		newChildDto.setMothersName("abc");
		Assert.assertEquals("PERSON_NOT_FOUND", operationsService.addChild(newChildDto));
	}

	@Test
	public void successfulMemberAdditionTest() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Dhruva");
		newMemberDto.setGender(Gender.MALE);
		Assert.assertEquals("MEMBER_ADDITION_SUCCEEDED", operationsService.addMember(newMemberDto));
	}

	@Test
	public void memberAdditionFailureWhenMemberIsAlreadyPresentTest() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Amba");
		newMemberDto.setGender(Gender.MALE);
		Assert.assertEquals("Member with the given name already exists!", operationsService.addMember(newMemberDto));
	}

	@Test
	public void memberAdditionFailureWhenMemberOfGenderIsNullTest() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Ambey");
		newMemberDto.setGender(null);
		Assert.assertEquals("Gender cannot be null!", operationsService.addMember(newMemberDto));
	}

	@Test
	public void successfulMarriageConductionTest() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Lara");
		newMemberDto.setGender(Gender.FEMALE);
		operationsService.addMember(newMemberDto);
		Assert.assertEquals("MARRIAGE_SUCCEEDED", operationsService.conductMarriage("Vasa", "Lara"));
	}

	@Test
	public void marriageConductionFailureWhenPersonIsNotFound() {
		Assert.assertEquals("PERSON_NOT_FOUND", operationsService.conductMarriage("Vasa", "Nidhi"));
	}

	@Test
	public void marriageConductionFailureWhenCoupleIsOfSameGender() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Naresh");
		newMemberDto.setGender(Gender.MALE);
		operationsService.addMember(newMemberDto);
		Assert.assertEquals("Only members of opposite gender can marry!",
				operationsService.conductMarriage("Vasa", "Naresh"));
	}

	@Test
	public void marriageConductionFailureWhenOnePersonIsAlreadyMarried() {
		NewMemberDto newMemberDto = new NewMemberDto();
		newMemberDto.setName("Manisha");
		newMemberDto.setGender(Gender.FEMALE);
		operationsService.addMember(newMemberDto);
		Assert.assertEquals("One or both of the people are already married!",
				operationsService.conductMarriage("Chit", "Manisha"));
	}

	@Test
	public void marriageConductionFailureWhenTwoPersonsAreAlreadyMarried() {
		Assert.assertEquals("One or both of the people are already married!",
				operationsService.conductMarriage("Chit", "Amba"));
	}

}
