package org.loyaltyengine.points_service.modules.points.dtos;

import lombok.Builder;
import lombok.Getter;
import org.loyaltyengine.points_service.shared.dtos.PageDto;

import java.util.List;

@Builder
@Getter
public class PointsResultDto {
    private PageDto page;
    private List<PointDto> points;
}
