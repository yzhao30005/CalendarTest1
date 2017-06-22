package com.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class EventApplication  {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}
}
