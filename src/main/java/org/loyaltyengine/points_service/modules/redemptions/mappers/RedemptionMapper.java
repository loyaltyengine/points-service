package org.loyaltyengine.points_service.modules.redemptions.mappers;

import org.loyaltyengine.points.v1.model.CreateRedemptionRequest;
import org.loyaltyengine.points.v1.model.CreateRedemptionRuleRequest;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.CreateRedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionDto;
import org.loyaltyengine.points_service.modules.redemptions.dtos.RedemptionRuleDto;
import org.loyaltyengine.points_service.modules.redemptions.models.Redemption;
import org.loyaltyengine.points_service.modules.redemptions.models.RedemptionRule;
import org.mapstruct.Mapper;

@Mapper
public interface RedemptionMapper {

    CreateRedemptionRuleDto toDto(CreateRedemptionRuleRequest request);

    org.loyaltyengine.points.v1.model.RedemptionRule toClient(RedemptionRuleDto dto);

    RedemptionRuleDto toDto(RedemptionRule rule);

    CreateRedemptionDto toDto(CreateRedemptionRequest request);

    RedemptionDto toDto(Redemption redemption);

    org.loyaltyengine.points.v1.model.Redemption toClient(RedemptionDto dto);

}
