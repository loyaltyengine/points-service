package org.loyaltyengine.points_service.modules.redemptions.repositories;

import org.loyaltyengine.points_service.modules.redemptions.models.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
}
