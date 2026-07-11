package org.loyaltyengine.points_service.modules.types.services;

import org.loyaltyengine.points_service.modules.types.dtos.CreatePointTypeDto;
import org.loyaltyengine.points_service.modules.types.dtos.PointTypeDto;

public interface PointTypeService {
    PointTypeDto getPropertyPointTypes(String propertyId);

    PointTypeDto getPointType(String propertyId, String pointTypeId);

    PointTypeDto createPointType(CreatePointTypeDto dto);
}
