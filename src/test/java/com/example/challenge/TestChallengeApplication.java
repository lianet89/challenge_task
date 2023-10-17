package com.example.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(ChallengeApplication::main).with(TestChallengeApplication.class).run(args);
	}

}
