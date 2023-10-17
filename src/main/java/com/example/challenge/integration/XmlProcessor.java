package com.example.challenge.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class XmlProcessor implements ISourceProcessor {

	@Override
	public JsonNode getRecords() {
		log.info("Fetching record from source B");
		String resourceBUrl = "http://localhost:7299/source/b";
		RestTemplate restTemplate = new RestTemplate();
		JsonNode node = null;

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(resourceBUrl, String.class);
			log.info(response.toString());

			XmlMapper xmlMapper = new XmlMapper();
			node = xmlMapper.readTree(response.getBody());

		} catch (Exception e) {
			log.info("Found a DEFECTIVE record (ignored)");
		}

		return node;
	}

	@Override
	public String getStatus(JsonNode node) {
		String status = "";
		if (node.toString().contains("done")) {
			status = "done";
		}
		if (node.toString().contains("value")) {
			status = "value";
		}
		return status;
	}

}
