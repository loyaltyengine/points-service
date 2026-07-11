package org.loyaltyengine.points_service.modules.points.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.loyaltyengine.points.v1.model.CreatePointRequest;
import org.loyaltyengine.points.v1.model.PointResponse;
import org.loyaltyengine.points.v1.model.PointsResponse;
import org.loyaltyengine.points.v1.model.Status;
import org.loyaltyengine.points_service.modules.points.dtos.CreatePointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointsResultDto;
import org.loyaltyengine.points_service.modules.points.mappers.PointMapper;
import org.loyaltyengine.points_service.modules.points.services.PointService;
import org.loyaltyengine.points_service.shared.dtos.PaginationQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PointController {
    public static final String CUSTOMER_POINTS_URL = "/properties/{propertyId}/customers/{customerId}/points";
    public static final String CUSTOMER_POINT_URL = "/properties/{propertyId}/customers/{customerId}/points/{pointId}";
    public static  final String PROPERTY_POINTS_URL = "/properties/{propertyId}/points";

    private final PointMapper pointMapper;
    private final PointService pointService;

    // Create points
    @PostMapping(value = CUSTOMER_POINTS_URL)
    public ResponseEntity<PointResponse> grantPoints(
            final @PathVariable String propertyId,
            final @PathVariable String customerId,
            final @RequestBody @Valid CreatePointRequest request) {
        // Create dto
        CreatePointDto createPointDto = pointMapper.toDto(request);
        createPointDto.setPropertyId(propertyId);
        createPointDto.setCustomerId(customerId);

        // Grant points
        PointDto pointDto = pointService.grantPoints(createPointDto);

        // Response
        PointResponse apiResponse = new PointResponse()
                .point(pointMapper.toClient(pointDto))
                .status(new Status().code(201).message("Points granted successfully"));

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping(value = CUSTOMER_POINTS_URL)
    public ResponseEntity<PointsResponse> getCustomerPoints(
            // Query parameters
            final @RequestParam(required = false, defaultValue = "0") Integer page,
            final @RequestParam(required = false, defaultValue = "100") Integer size,
            final @RequestParam(required = false, defaultValue = "createdAt") String sort,
            final @RequestParam(required = false, defaultValue = "asc") String order,
            // Path parameters
            final @PathVariable String propertyId,
            final @PathVariable String customerId){

        // Get points
        PointsResultDto result = pointService.getCustomerPoints(propertyId, customerId,
                PaginationQueryDto.builder()
                        .page(page)
                        .size(size)
                        .sort(sort)
                        .order(order)
                        .build());
        // Response
        PointsResponse apiResponse = new PointsResponse()
                .page(pointMapper.toClient(result.getPage()))
                .points(pointMapper.toClient(result.getPoints()))
                .status(new Status().code(200).message("Points retrieved successfully"));

        return ResponseEntity.ok(apiResponse);
    }
}
