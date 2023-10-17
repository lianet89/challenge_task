package com.example.challenge.integration;

import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JsonNode;

public interface ISourceProcessor {

	public static AtomicInteger doneCounter = new AtomicInteger(0);

	public JsonNode getRecords();

	public String getStatus(JsonNode node);
}
