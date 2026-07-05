package org.loyaltyengine.points_service.modules.coupons.mappers;

import openapitools.CouponsModels;
import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.mapstruct.Mapper;

@Mapper
public interface CouponMapper {

    CouponDto toDto(CouponsModels.CouponResponse response);
}
