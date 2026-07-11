package org.loyaltyengine.points_service.modules.points.services;

import org.loyaltyengine.points_service.modules.points.dtos.DebitPointsResultDto;
import org.loyaltyengine.points_service.modules.points.dtos.CreatePointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointDto;
import org.loyaltyengine.points_service.modules.points.dtos.PointsResultDto;
import org.loyaltyengine.points_service.shared.dtos.PaginationQueryDto;

public interface PointService {

    /**
     * Grants points to a customer.
     *
     * @param dto the data transfer object containing the point details
     * @return the created point dto
     */
    public PointDto grantPoints(CreatePointDto dto);

    /**
     * Get customer points
     * @param propertyId the propertyId
     * @param customerId the customerId
     * @param paginationQueryDto pagination info
     * @return paginated points
     */
    public PointsResultDto getCustomerPoints(String propertyId, String customerId, PaginationQueryDto paginationQueryDto);

    public PointDto getPoint(String propertyId, String customerId, String pointId);

    public DebitPointsResultDto debitPoints(String propertyId, String customerId, Integer points);
}
