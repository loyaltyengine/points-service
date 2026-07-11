package org.loyaltyengine.points_service.modules.points.mappers;

import org.loyaltyengine.points.v1.model.CreatePointRequest;
import org.loyaltyengine.points.v1.model.Page;
import org.loyaltyengine.points_service.modules.points.dtos.CreatePointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointDto;
import org.loyaltyengine.points_service.modules.points.models.Point;
import org.loyaltyengine.points_service.shared.dtos.PageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    PointDto toDto(Point point);

    CreatePointDto toDto(CreatePointRequest request);

    org.loyaltyengine.points.v1.model.Point toClient(PointDto pointDto);

    List<PointDto> toDto(List<Point> points);

    Page toClient(PageDto page);

    List<org.loyaltyengine.points.v1.model.Point> toClient(List<PointDto> points);
}
