package com.geektrust.family.services;

public interface CommandService {
	
	public String executeCommandAndProduceResult(String[] words, OperationsService operationsService);

}
