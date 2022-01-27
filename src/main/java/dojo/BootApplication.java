package dojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class BootApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(BootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
		LOGGER.info("Spring boot api started.");
	}
}
