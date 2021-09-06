package com.geektrust.family.services;

import com.geektrust.family.customexceptions.GenderException;
import com.geektrust.family.customexceptions.IdentityException;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.dtos.NewMemberDto;
import com.geektrust.family.dtos.RoyalPair;
import com.geektrust.family.models.Gender;
import com.geektrust.family.models.RoyalFamily;

public class InitiationService {

	private CeremonialService ceremonialService;
	private String queensName;

	public InitiationService(RoyalPair royalPair) {
		// 1st Generation
		ceremonialService = new CeremonialService(new RoyalFamily(royalPair));
		this.queensName = royalPair.getQueensName();
	}
	
	public RoyalFamily initiateFamilyTree() throws IdentityException, GenderException {
		createSecondGeneration();
		createThirdGeneration();
		createFourthGeneration();
		return ceremonialService.getRoyalFamily();
	}

	private void createSecondGeneration() throws IdentityException, GenderException {
		// Chit, Ish, Vich, Aras, Satya
		ceremonialService.addMember(new NewChildDto(queensName, "Chit", Gender.MALE));
		ceremonialService.addMember(new NewChildDto(queensName, "Ish", Gender.MALE));
		ceremonialService.addMember(new NewChildDto(queensName, "Vich", Gender.MALE));
		ceremonialService.addMember(new NewChildDto(queensName, "Aras", Gender.MALE));
		ceremonialService.addMember(new NewChildDto(queensName, "Satya", Gender.FEMALE));

		// Amba, Lika, Chitra, Vyan
		ceremonialService.addMember(new NewMemberDto("Amba", Gender.FEMALE));
		ceremonialService.addMember(new NewMemberDto("Lika", Gender.FEMALE));
		ceremonialService.addMember(new NewMemberDto("Chitra", Gender.FEMALE));
		ceremonialService.addMember(new NewMemberDto("Vyan", Gender.MALE));

		ceremonialService.conductMarriage("Chit", "Amba");
		ceremonialService.conductMarriage("Vich", "Lika");
		ceremonialService.conductMarriage("Aras", "Chitra");
		ceremonialService.conductMarriage("Satya", "Vyan");
		
	}

	private void createThirdGeneration() throws IdentityException, GenderException {
		// Dritha, Tritha, Vritha
		ceremonialService.addMember(new NewChildDto("Amba", "Dritha", Gender.FEMALE));
		ceremonialService.addMember(new NewChildDto("Amba", "Tritha", Gender.FEMALE));
		ceremonialService.addMember(new NewChildDto("Amba", "Vritha", Gender.MALE));

		// Vila, Chika
		ceremonialService.addMember(new NewChildDto("Lika", "Vila", Gender.FEMALE));
		ceremonialService.addMember(new NewChildDto("Lika", "Chika", Gender.FEMALE));

		// Jnki, Ahit
		ceremonialService.addMember(new NewChildDto("Chitra", "Jnki", Gender.FEMALE));
		ceremonialService.addMember(new NewChildDto("Chitra", "Ahit", Gender.MALE));

		// Asva, Vyas, Atya
		ceremonialService.addMember(new NewChildDto("Satya", "Asva", Gender.MALE));
		ceremonialService.addMember(new NewChildDto("Satya", "Vyas", Gender.MALE));
		ceremonialService.addMember(new NewChildDto("Satya", "Atya", Gender.FEMALE));

		// ------
		// Jaya
		ceremonialService.addMember(new NewMemberDto("Jaya", Gender.MALE));

		// Arit
		ceremonialService.addMember(new NewMemberDto("Arit", Gender.MALE));

		// Satvy, Krpi
		ceremonialService.addMember(new NewMemberDto("Satvy", Gender.FEMALE));
		ceremonialService.addMember(new NewMemberDto("Krpi", Gender.FEMALE));

		ceremonialService.conductMarriage("Dritha", "Jaya");
		ceremonialService.conductMarriage("Jnki", "Arit");
		ceremonialService.conductMarriage("Asva", "Satvy");
		ceremonialService.conductMarriage("Vyas", "Krpi");
	}
	
	private void createFourthGeneration() throws IdentityException, GenderException {
		//Yodhan
		ceremonialService.addMember(new NewChildDto("Dritha", "Yodhan", Gender.MALE));

		//Laki, Lavnya
		ceremonialService.addMember(new NewChildDto("Jnki","Laki", Gender.MALE));
		ceremonialService.addMember(new NewChildDto("Jnki","Lavnya", Gender.FEMALE));

		//Vasa
		ceremonialService.addMember(new NewChildDto("Satvy", "Vasa", Gender.MALE));

		//Kriya, Krithi
		ceremonialService.addMember(new NewChildDto("Krpi", "Kriya", Gender.MALE));
		ceremonialService.addMember(new NewChildDto("Krpi", "Krithi", Gender.FEMALE));
	}

}
