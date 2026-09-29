package org.loyaltyengine.points_service.modules.templates.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.loyaltyengine.points.client.models.CreatePointTemplateRequest;
import org.loyaltyengine.points.client.models.PointTemplateResponse;
import org.loyaltyengine.points.client.models.Status;
import org.loyaltyengine.points_service.modules.templates.dtos.CreatePointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.dtos.PointTemplateDto;
import org.loyaltyengine.points_service.modules.templates.mappers.PointTemplateMapper;
import org.loyaltyengine.points_service.modules.templates.services.PointTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("points-api/v1")
@RequiredArgsConstructor
public class PointTemplateController {
    private static final String PROPERTY_POINT_TYPES_URL = "/properties/{propertyId}/point-templates";

    private final PointTemplateService service;
    private final PointTemplateMapper mapper;

    @PostMapping(value = PROPERTY_POINT_TYPES_URL)
    public ResponseEntity<PointTemplateResponse> createType(final @PathVariable String propertyId,
                                                            final @RequestBody @Valid CreatePointTemplateRequest request) {
        // Create
        CreatePointTemplateDto dto = mapper.toDto(request);
        dto.setPropertyId(propertyId);

        PointTemplateDto type = service.createPointTemplate(dto);

        // Response
        PointTemplateResponse response = new PointTemplateResponse()
                .status(new Status().code(201).message("Point type created"))
                .pointType(mapper.toClient(type));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
