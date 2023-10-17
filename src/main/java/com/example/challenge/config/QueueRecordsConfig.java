package com.example.challenge.config;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueRecordsConfig {

	@Bean
	public BlockingQueue<String> recordsQueue() {
		return new LinkedBlockingDeque<>();
	}
}
