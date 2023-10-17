package com.example.challenge.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.challenge.dto.SinkDto;
import com.example.challenge.enums.Kind;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Sender {

	public String postSinkA(Kind kind, String id) throws JsonProcessingException {
		log.info("Sending record to sink A: " , id);
		String sinkAUrl = "http://localhost:7299/sink/a";

		ObjectMapper objectMapper = new ObjectMapper();
		SinkDto sinkADto = new SinkDto(kind.toString(), id);
		String json = objectMapper.writeValueAsString(sinkADto);

		HttpEntity<String> request = new HttpEntity<>(json);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SinkDto> response = restTemplate.exchange(sinkAUrl, HttpMethod.POST, request, SinkDto.class);

		return response.getStatusCode().toString();
	}

}
