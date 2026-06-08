package org.loyaltyengine.points_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class PointsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointsServiceApplication.class, args);
	}

	@Bean
	public DateTimeProvider utcDateTimeProvider() {
		return () -> Optional.of(OffsetDateTime.now());
	}

}
