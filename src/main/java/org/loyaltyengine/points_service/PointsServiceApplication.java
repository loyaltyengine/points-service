package org.loyaltyengine.points_service;

import openapitools.services.couponsservice.CouponsServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.grpc.client.ImportGrpcClients;

import java.time.OffsetDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
@ImportGrpcClients(target = "coupons-service", types = CouponsServiceGrpc.CouponsServiceBlockingStub.class)
public class PointsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointsServiceApplication.class, args);
	}

	@Bean
	public DateTimeProvider utcDateTimeProvider() {
		return () -> Optional.of(OffsetDateTime.now());
	}

}
