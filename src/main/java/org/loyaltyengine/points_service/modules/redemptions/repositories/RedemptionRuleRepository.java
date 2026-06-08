package org.loyaltyengine.points_service.modules.redemptions.repositories;

import org.loyaltyengine.points_service.modules.redemptions.models.RedemptionRule;
import org.loyaltyengine.points_service.modules.redemptions.utils.CouponType;
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedemptionRuleRepository extends JpaRepository<RedemptionRule, String> {

    Optional<RedemptionRule> findByPropertyIdAndRedemptionTypeAndCouponType(String propertyId,
                                                                                   RedemptionType redemptionType,
                                                                                   CouponType couponType);

    Optional<RedemptionRule> findByPropertyIdAndRedemptionType(String propertyId,
                                                                            RedemptionType redemptionType);

}
