package org.loyaltyengine.points_service.modules.types.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.loyaltyengine.points.v1.model.CreatePointTypeRequest;
import org.loyaltyengine.points.v1.model.PointTypeResponse;
import org.loyaltyengine.points.v1.model.Status;
import org.loyaltyengine.points_service.modules.types.dtos.CreatePointTypeDto;
import org.loyaltyengine.points_service.modules.types.dtos.PointTypeDto;
import org.loyaltyengine.points_service.modules.types.mappers.PointTypeMapper;
import org.loyaltyengine.points_service.modules.types.services.PointTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PointTypeController {
    private static final String PROPERTY_POINT_TYPES_URL = "/properties/{propertyId}/types";

    private final PointTypeService service;
    private final PointTypeMapper mapper;

    @PostMapping(value = PROPERTY_POINT_TYPES_URL)
    public ResponseEntity<PointTypeResponse> createType(final @PathVariable String propertyId,
                                                        final @RequestBody @Valid CreatePointTypeRequest request) {
        // Create
        CreatePointTypeDto dto = mapper.toDto(request);
        dto.setPropertyId(propertyId);

        PointTypeDto type = service.createPointType(dto);

        // Response
        PointTypeResponse response = new PointTypeResponse()
                .status(new Status().code(201).message("Point type created"))
                .pointType(mapper.toClient(type));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
