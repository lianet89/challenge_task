package com.example.challenge.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonProcessor implements ISourceProcessor {

	@Override
	public JsonNode getRecords() {
		log.info("Fetching record from source A");
		String resourceAUrl = "http://localhost:7299/source/a";
		RestTemplate restTemplate = new RestTemplate();
		JsonNode node = null;

		try {
			ResponseEntity<JsonNode> response = restTemplate.getForEntity(resourceAUrl, JsonNode.class);
			log.info(response.toString());
			node = response.getBody();
		} catch (Exception e) {
			log.info("Found a DEFECTIVE record (ignored)");
		}
		return node;
	}

	@Override
	public String getStatus(JsonNode node) {
		return node.path("status").toString();
	}

}
