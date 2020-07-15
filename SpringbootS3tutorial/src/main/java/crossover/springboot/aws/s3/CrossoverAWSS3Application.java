package crossover.springboot.aws.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class CrossoverAWSS3Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrossoverAWSS3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(CrossoverAWSS3Application.class, args);
		LOGGER.info("SpringbootS3tutorial application started successfully.");
	}
}
