package com.example.challenge.service;

import java.util.concurrent.BlockingQueue;

import com.example.challenge.enums.Kind;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Categorizer {

	@Autowired
	BlockingQueue<String> queueRecords;

	@Autowired
	Sender sender;

	@Async
	public void categorizeJoined(String id) throws JsonProcessingException {
		if (!queueRecords.isEmpty() && queueRecords.contains(id)) {
			sender.postSinkA(Kind.JOINED, id);
			queueRecords.remove(id);
			log.info("Category: " + Kind.JOINED.toString());
		} else {
			queueRecords.add(id);
		}
	}

	public void categorizeOrphaned() throws JsonProcessingException {
		for (String id : queueRecords) {
			sender.postSinkA(Kind.ORPHANED, id);
			log.info("Category: " + Kind.ORPHANED.toString());
		}
	}
}
