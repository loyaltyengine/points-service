package org.loyaltyengine.points_service.modules.coupons.mappers;

import openapitools.CouponsModels;

import org.loyaltyengine.coupons.v1.model.CouponResponse;
import org.loyaltyengine.coupons.v1.model.FixedAmountCoupon;
import org.loyaltyengine.coupons.v1.model.PercentageCoupon;
import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface CouponMapper {
    CouponDto toDto(CouponsModels.Coupon coupon);

    CouponDto toDto(FixedAmountCoupon fixedAmountCoupon);

    CouponDto toDto(PercentageCoupon percentageCoupon);

}
