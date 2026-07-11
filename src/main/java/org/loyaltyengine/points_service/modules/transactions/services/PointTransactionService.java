package org.loyaltyengine.points_service.modules.transactions.services;

import org.loyaltyengine.points_service.modules.transactions.dtos.CreatePointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.dtos.PointTransactionDto;

public interface PointTransactionService {
    PointTransactionDto createPointTransaction(CreatePointTransactionDto dto);
}
