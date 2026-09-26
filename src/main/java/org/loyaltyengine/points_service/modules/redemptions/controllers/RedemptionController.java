package org.loyaltyengine.points_service.modules.redemptions.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.loyaltyengine.points.v1.model.CreateRedemptionRequest;
import org.loyaltyengine.points.v1.model.CreateRedemptionRuleRequest;
import org.loyaltyengine.points.v1.model.RedemptionResponse;
import org.loyaltyengine.points.v1.model.RedemptionRuleResponse;
import org.loyaltyengine.points.v1.model.Status;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.mappers.RedemptionMapper;
import org.loyaltyengine.points_service.modules.redemptions.services.RedemptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("points-api/v1")
@RequiredArgsConstructor
public class RedemptionController {
    public static final String PROPERTY_RULES_URL = "/properties/{propertyId}/redemptions/rules";
    public static final String CUSTOMER_REDEMPTIONS_URL = "/properties/{propertyId}/customers/{customerId}/redemptions";

    private final RedemptionService service;
    private final RedemptionMapper mapper;

    @PostMapping(PROPERTY_RULES_URL)
    public ResponseEntity<RedemptionRuleResponse> createRedemptionRule(
            final @PathVariable String propertyId,
            final @Valid @RequestBody CreateRedemptionRuleRequest request) {
        // Request
        CreateRedemptionRuleDto dto = mapper.toDto(request);
        dto.setPropertyId(propertyId);

        // Create redemption rule
        RedemptionRuleDto rule = service.createRedemptionRule(dto);

        // Response
        RedemptionRuleResponse response = new RedemptionRuleResponse()
                .status(new Status().code(201).message("Redemption rule created"))
                .redemptionRule(mapper.toClient(rule));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(CUSTOMER_REDEMPTIONS_URL)
    public ResponseEntity<RedemptionResponse> createRedemption(
            final @PathVariable String propertyId,
            final @PathVariable String customerId,
            final @Valid @RequestBody CreateRedemptionRequest request) {
        CreateRedemptionDto dto = mapper.toDto(request);
        dto.setPropertyId(propertyId);
        dto.setCustomerId(customerId);

        RedemptionDto redemption = service.createRedemption(dto);

        RedemptionResponse response = new RedemptionResponse()
                .status(new Status().code(201).message("Redemption created"))
                .redemption(mapper.toClient(redemption));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}