package org.loyaltyengine.points_service.modules.coupons.services;

import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.loyaltyengine.points_service.modules.coupons.dtos.CreateCouponDto;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceGrpcImpl implements CouponService{
    @Override
    public CouponDto createCoupon(CreateCouponDto createCouponDto) {
        // Return null if the response is not received or not 201, and log response
        return null;
    }
}
