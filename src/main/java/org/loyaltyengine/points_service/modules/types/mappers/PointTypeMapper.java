package org.loyaltyengine.points_service.modules.types.mappers;

import org.loyaltyengine.points.v1.model.CreatePointTypeRequest;
import org.loyaltyengine.points_service.modules.types.dtos.CreatePointTypeDto;
import org.loyaltyengine.points_service.modules.types.dtos.PointTypeDto;
import org.loyaltyengine.points_service.modules.types.models.PointType;
import org.mapstruct.Mapper;

@Mapper
public interface PointTypeMapper {

    CreatePointTypeDto toDto(CreatePointTypeRequest request);

    PointType toModel(CreatePointTypeDto dto);

    PointTypeDto toDto(PointType pointType);

    org.loyaltyengine.points.v1.model.PointType toClient(PointTypeDto pointTypeDto);
}
