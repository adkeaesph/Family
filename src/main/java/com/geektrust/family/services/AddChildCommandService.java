package com.geektrust.family.services;

import com.geektrust.family.dtos.NewChildDto;
import com.geektrust.family.models.Gender;

public class AddChildCommandService implements CommandService {
	
	@Override
	public String executeCommandAndProduceResult(String[] words, OperationsService operationsService) {
		NewChildDto newChildDto = new NewChildDto();
		newChildDto.setMothersName(words[1]);
		newChildDto.setChildsName(words[2]);
		newChildDto.setChildsGender(getGenderFromString(words[3]));
		return operationsService.addChild(newChildDto);
	}
	
	private Gender getGenderFromString(String gender) {
		if (gender.toUpperCase().equals("MALE"))
			return Gender.MALE;
		else if (gender.toUpperCase().equals("FEMALE"))
			return Gender.FEMALE;
		return null;
	}

}
