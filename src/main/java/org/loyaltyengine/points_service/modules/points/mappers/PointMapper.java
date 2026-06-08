package org.loyaltyengine.points_service.modules.points.mappers;

import org.loyaltyengine.openapi.model.CreatePointRequest;
import org.loyaltyengine.openapi.model.Page;
import org.loyaltyengine.points_service.modules.points.dtos.CreatePointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointDto;
import org.loyaltyengine.points_service.modules.points.models.Point;
import org.loyaltyengine.points_service.shared.dtos.PageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    PointDto toDto(Point point);

    CreatePointDto toCreateDto(CreatePointRequest request);

    org.loyaltyengine.openapi.model.Point toClientPoint(PointDto pointDto);

    List<PointDto> toDtoList(List<Point> points);

    Page toClientPage(PageDto page);

    List<org.loyaltyengine.openapi.model.Point> toClientPointList(List<PointDto> points);
}
