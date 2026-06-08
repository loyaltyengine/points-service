package org.loyaltyengine.points_service.modules.transactions.mappers;


import org.loyaltyengine.points_service.modules.transactions.dtos.CreatePointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.dtos.PointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.models.PointTransaction;
import org.mapstruct.Mapper;

@Mapper
public interface PointTransactionMapper {

    PointTransactionDto toDto(PointTransaction pointTransaction);

    PointTransaction toEntity(CreatePointTransactionDto pointTransactionDto);
}
