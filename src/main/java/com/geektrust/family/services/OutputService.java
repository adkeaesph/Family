package com.geektrust.family.services;

import java.io.IOException;
import java.util.List;

import com.geektrust.family.models.RoyalFamily;

public class OutputService {

	private OperationsService operationsService;
	private AddChildCommandService addChildCommandService;
	private GetRelationshipCommandService getRelationshipCommandService;

	public OutputService(RoyalFamily royalFamily) {
		operationsService = new OperationsService(royalFamily);
		addChildCommandService = new AddChildCommandService();
		getRelationshipCommandService = new GetRelationshipCommandService();
	}

	public String readFileAndProduceResult(String filepath) throws IOException {
		List<String> commands = UtilService.getListOfCommands(filepath);
		String[] words;
		String result = "";
		try {
			for (String command : commands) {
				words = UtilService.getIndividualWordsFromSentence(command);
				if (words[0].equals("ADD_CHILD")) {
					result += addChildCommandService.executeCommandAndProduceResult(words, operationsService) + "\n";
				} else if (words[0].equals("GET_RELATIONSHIP")) {
					result += getRelationshipCommandService.executeCommandAndProduceResult(words, operationsService)
							+ "\n";
				} else
					throw new Exception("Wrong file format!!!");
			}
			result = UtilService.removeLeadingAndTrailingSpacesFromString(result);
		} catch (Exception exception) {
			return exception.getMessage();
		}
		return result;
	}
}
