package org.loyaltyengine.points_service.modules.types.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.points.v1.model.ErrorType;
import org.loyaltyengine.points_service.common.exceptions.NotFoundException;
import org.loyaltyengine.points_service.modules.types.dtos.CreatePointTypeDto;
import org.loyaltyengine.points_service.modules.types.dtos.PointTypeDto;
import org.loyaltyengine.points_service.modules.types.mappers.PointTypeMapper;
import org.loyaltyengine.points_service.modules.types.models.PointType;
import org.loyaltyengine.points_service.modules.types.repositories.PointTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointTypeServiceImpl implements PointTypeService {
    private final PointTypeRepository repository;
    private final PointTypeMapper mapper;

    @Override
    public PointTypeDto createPointType(CreatePointTypeDto dto) {
        log.info("Creating point type for propertyId {}", dto.getPropertyId());
        PointType newPointType = mapper.toModel(dto);
        PointType savedPointType = repository.save(newPointType);
        return mapper.toDto(savedPointType);
    }

    @Override
    public PointTypeDto getPropertyPointTypes(String propertyId) {
        return null;
    }

    @Override
    public PointTypeDto getPointType(String propertyId, String pointTypeId) {
        PointType type = repository.findByPointTypeIdAndPropertyId(pointTypeId, propertyId)
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND, "Not found",
                        "Point type with id " + pointTypeId + " not found"));

        return mapper.toDto(type);
    }


}
