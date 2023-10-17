package com.example.challenge;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.challenge.service.processors.ISourceProcessorService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class ChallengeApplication implements CommandLineRunner {
	static AtomicInteger doneCounter = new AtomicInteger(0);

	@Autowired
	private List<ISourceProcessorService> processors;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting system.");

		for (ISourceProcessorService processor : processors) {
			processor.processRecords();
		}

	}

}
