package com.geektrust.family.services;

import com.geektrust.family.dtos.NameRelationshipPair;

public class GetRelationshipCommandService implements CommandService {

	@Override
	public String executeCommandAndProduceResult(String[] words, OperationsService operationsService) {
		NameRelationshipPair nameRelationshipPair = new NameRelationshipPair();
		nameRelationshipPair.setName(words[1]);
		nameRelationshipPair.setRelationship(words[2]);
		return operationsService.getRelationship(nameRelationshipPair);
	}

}
