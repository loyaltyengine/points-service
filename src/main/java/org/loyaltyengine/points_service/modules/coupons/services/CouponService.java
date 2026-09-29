package org.loyaltyengine.points_service.modules.coupons.services;

import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.loyaltyengine.points_service.modules.coupons.dtos.CreateCouponDto;

public interface CouponService {

    /**
     * Create a new coupon
     *
     * @param dto coupon dto
     * @return CouponDto
     */
    public CouponDto createCoupon(CreateCouponDto dto);

}
