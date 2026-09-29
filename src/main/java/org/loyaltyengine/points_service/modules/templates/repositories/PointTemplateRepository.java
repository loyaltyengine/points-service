package org.loyaltyengine.points_service.modules.templates.repositories;

import org.loyaltyengine.points_service.modules.templates.models.PointTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointTemplateRepository extends JpaRepository<PointTemplate, String> {

    Optional<PointTemplate> findByPointTemplateIdAndPropertyId(String pointTemplateId, String propertyId);
}
