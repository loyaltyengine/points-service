package org.loyaltyengine.points_service.modules.templates.mappers;

import org.loyaltyengine.points.client.models.CreatePointTemplateRequest;
import org.loyaltyengine.points_service.modules.templates.dtos.CreatePointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.dtos.PointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.models.PointTemplate;
import org.mapstruct.Mapper;

@Mapper
public interface PointTemplateMapper {

    CreatePointTemplateDto toDto(CreatePointTemplateRequest request);

    PointTemplate toModel(CreatePointTemplateDto dto);

    PointTemplateDto toDto(PointTemplate pointTemplate);

    org.loyaltyengine.points.client.models.PointTemplate toClient(PointTemplateDto pointTemplateDto);
}
