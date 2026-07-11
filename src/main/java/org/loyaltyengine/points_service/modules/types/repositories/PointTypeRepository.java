package org.loyaltyengine.points_service.modules.types.repositories;

import org.loyaltyengine.points_service.modules.types.models.PointType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointTypeRepository extends JpaRepository<PointType, String> {

    Optional<PointType> findByPointTypeIdAndPropertyId(String pointTypeId, String propertyId);
}
