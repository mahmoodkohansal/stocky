package ir.mahmood.sahame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SahameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahameApplication.class, args);
	}

}
