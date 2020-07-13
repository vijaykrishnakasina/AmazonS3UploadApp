package org.jcg.springboot.aws.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class SpringbootS3tutorial {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootS3tutorial.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootS3tutorial.class, args);
		LOGGER.info("SpringbootS3tutorial application started successfully.");
	}
}
