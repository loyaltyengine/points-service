package org.loyaltyengine.points_service.modules.templates.services;

import org.loyaltyengine.points_service.modules.templates.dtos.CreatePointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.dtos.PointTemplateDto;

public interface PointTemplateService {
    PointTemplateDto getPointTemplate(String propertyId, String pointTypeId);

    PointTemplateDto createPointTemplate(CreatePointTemplateDto dto);
}
