package org.loyaltyengine.points_service.modules.redemptions.controllers;

import lombok.RequiredArgsConstructor;
import org.loyaltyengine.points_service.modules.redemptions.services.RedemptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RedemptionController {
    public static  final String PROPERTY_RULES_URL = "/properties/{propertyId}/rules";

    private final RedemptionService redemptionService;
}
