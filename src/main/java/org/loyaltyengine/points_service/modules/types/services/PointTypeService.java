package org.loyaltyengine.points_service.modules.types.services;

import org.loyaltyengine.points_service.modules.types.dtos.PointTypeDto;

public interface PointTypeService {
    public PointTypeDto getPropertyPointTypes(String propertyId);

    public PointTypeDto getPointType(String propertyId, String pointTypeId);
}
