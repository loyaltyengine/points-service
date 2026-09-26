package org.loyaltyengine.points_service.modules.coupons.services;

import lombok.RequiredArgsConstructor;
import openapitools.CouponsModels;
import openapitools.services.couponsservice.CouponsServiceGrpc;
import openapitools.services.couponsservice.CouponsServiceOuterClass;
import org.loyaltyengine.points_service.modules.coupons.dtos.CouponDto;
import org.loyaltyengine.points_service.modules.coupons.dtos.CreateCouponDto;
import org.loyaltyengine.points_service.modules.coupons.mappers.CouponMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceGrpcImpl implements CouponService {

    private final CouponsServiceGrpc.CouponsServiceBlockingStub stub;
    private final CouponMapper mapper;

    @Override
    public CouponDto createCoupon(CreateCouponDto dto) {
//        CouponsServiceOuterClass.CreateCouponRequest request = CouponsServiceOuterClass.CreateCouponRequest.newBuilder()
//                .setPropertyId(dto.getPropertyId())
//                .setCustomerId(dto.getCustomerId())
//                .setBaseCreateCouponRequest(CouponsModels.BaseCreateCouponRequest.newBuilder()
//                        .build())
//                .build();
//
//        CouponsModels.CouponResponse response = stub.createCoupon(request);
//        if (response.getStatus().getCode() == 201) {
//            return mapper.toDto(response);
//        }
//
//        // Return null if the response is not received or not 201, and log response
        return null;
    }
}
