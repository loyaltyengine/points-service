package org.loyaltyengine.points_service.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaginationQueryDto {
    private Integer page;
    private Integer size;
    private String sort;
    private String order;
}
