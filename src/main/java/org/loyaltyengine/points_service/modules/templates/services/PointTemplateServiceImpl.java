package org.loyaltyengine.points_service.modules.templates.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.points.client.models.ErrorType;
import org.loyaltyengine.points_service.core.exceptions.BadRequestException;
import org.loyaltyengine.points_service.core.exceptions.NotFoundException;
import org.loyaltyengine.points_service.modules.templates.dtos.CreatePointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.dtos.PointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.mappers.PointTemplateMapper;
import org.loyaltyengine.points_service.modules.templates.models.PointTemplate;
import org.loyaltyengine.points_service.modules.templates.repositories.PointTemplateRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointTemplateServiceImpl implements PointTemplateService {
    private final PointTemplateRepository repository;
    private final PointTemplateMapper mapper;

    @Override
    public PointTemplateDto createPointTemplate(CreatePointTemplateDto dto) {
        log.info("Creating a point template for propertyId {}", dto.getPropertyId());

        // Not validated by controller since they are both optional on request level, but at least one of them must be provided
        if (dto.getNumberOfPoints() == null && dto.getValidNumberOfDays() == null) {
            throw new BadRequestException(ErrorType.INVALID_REQUEST, "Bad request",
                    "Number of points or valid number of days must be provided");
        }

        // Map request to model
        PointTemplate newPointTemplate = mapper.toModel(dto);

        // Create point template
        PointTemplate savedPointTemplate = repository.save(newPointTemplate);

        return mapper.toDto(savedPointTemplate);
    }

    @Override
    public PointTemplateDto getPointTemplate(String propertyId, String pointTemplateId) {
        PointTemplate template = repository.findByPointTemplateIdAndPropertyId(pointTemplateId, propertyId)
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND, "Not found",
                        "Point template with id " + pointTemplateId + " not found"));

        return mapper.toDto(template);
    }


}
