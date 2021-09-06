package com.geektrust.family.services;

import java.io.IOException;
import java.util.List;

import com.geektrust.family.dtos.NameRelationshipPair;
import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.models.Gender;
import com.geektrust.family.models.RoyalFamily;

public class OutputService {

	private UtilService utilService;
	private OperationsService operationsService;

	public OutputService(RoyalFamily royalFamily) {
		utilService = new UtilService();
		operationsService = new OperationsService(royalFamily);
	}

	public void readFileAndProduceResult(String filepath) throws IOException {
		List<String> commands = utilService.getListOfCommands(filepath);
		String[] words;
		String result = "";
		try {
			for (String command : commands) {
				words = command.split("\\s+");
				if (words[0].equals("ADD_CHILD")) {
					NewChildDto newChildDto = new NewChildDto();
					newChildDto.setMothersName(words[1]);
					newChildDto.setChildsName(words[2]);
					newChildDto.setChildsGender(getGenderFromString(words[3]));
					result += operationsService.addChild(newChildDto) + "\n";
				} else if (words[0].equals("GET_RELATIONSHIP")) {
					NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
					nameRelationshipPair.setName(words[1]);
					nameRelationshipPair.setRelationship(words[2]);
					result += operationsService.getRelationship(nameRelationshipPair) + "\n";
				}
			}
		} catch (Exception exception) {
			produceOutcome("Wrong file format!!!");
		}
		produceOutcome(result);
	}

	private Gender getGenderFromString(String gender) {
		if (gender.toUpperCase().equals("MALE"))
			return Gender.MALE;
		else if (gender.toUpperCase().equals("FEMALE"))
			return Gender.FEMALE;
		return null;
	}
	
	private void produceOutcome(String result) {
		System.out.println(result);		
	}

}
