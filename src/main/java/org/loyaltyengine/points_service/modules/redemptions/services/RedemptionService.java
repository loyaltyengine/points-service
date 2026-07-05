package org.loyaltyengine.points_service.modules.redemptions.services;

import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionRuleDto;

public interface RedemptionService {

    RedemptionRuleDto createRedemptionRule(CreateRedemptionRuleDto dto);

    RedemptionDto createRedemption(CreateRedemptionDto dto);
}
