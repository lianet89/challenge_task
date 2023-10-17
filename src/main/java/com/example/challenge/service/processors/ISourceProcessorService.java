package com.example.challenge.service.processors;

import org.springframework.scheduling.annotation.Async;

public interface ISourceProcessorService {

	@Async
	public void processRecords() throws Exception;

}
