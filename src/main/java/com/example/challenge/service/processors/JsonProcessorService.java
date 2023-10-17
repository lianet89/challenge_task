package com.example.challenge.service.processors;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.challenge.enums.Status;
import com.example.challenge.integration.JsonProcessor;
import com.example.challenge.service.Categorizer;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JsonProcessorService implements ISourceProcessorService {
	@Autowired
	JsonProcessor jsonProcessor;

	@Autowired
	Categorizer categorizer;

	AtomicBoolean done = new AtomicBoolean(false);

	@Async
	@Override
	public void processRecords() throws Exception {

		while (!done.get()) {
			try {
				JsonNode value = jsonProcessor.getRecords();
				if (value != null && !value.isNull()) {
					String status = jsonProcessor.getStatus(value);
					if (status.equalsIgnoreCase(Status.DONE.status)) {
						log.info("FINAL record from source A.");
						done.set(true);
					} else {
						String id = value.path("id").toString();
						categorizer.categorizeJoined(id);
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				categorizer.categorizeOrphaned();
			}
		}

	}

}
