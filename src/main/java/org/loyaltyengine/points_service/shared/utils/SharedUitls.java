package org.loyaltyengine.points_service.shared.utils;

import org.loyaltyengine.points_service.shared.constants.SharedConstants;
import org.loyaltyengine.points_service.shared.dtos.PaginationQueryDto;
import org.loyaltyengine.points_service.shared.enums.PointSortField;
import org.loyaltyengine.points_service.shared.enums.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class SharedUitls {
    private SharedUitls() {}

    public static Pageable buildValidPageable(PaginationQueryDto dto) {
        int size = dto.getSize() < 0 || dto.getSize() > SharedConstants.MAX_PAGE_SIZE ? SharedConstants.MAX_PAGE_SIZE
                : dto.getSize();
        int page = dto.getPage() < SharedConstants.MIN_PAGE_NUMBER ? SharedConstants.MIN_PAGE_NUMBER : dto.getPage();

        String sort = PointSortField.fromValue(dto.getSort()).getValue();
        String order = SortOrder.fromValue(dto.getOrder()).getValue();
        Sort.Direction direction = dto.getOrder().equalsIgnoreCase(order)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(
                page,
                size,
                Sort.by(direction, sort));
    }
}
