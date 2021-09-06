package com.geektrust.family.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class UtilService {

	UtilService() {
	}
	
	List<String> getListOfCommands(String filepath) throws IOException {
		File file = new File(filepath);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		List<String> commands = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			commands.add(line);
		}
		br.close();
		return commands;
	}

}
