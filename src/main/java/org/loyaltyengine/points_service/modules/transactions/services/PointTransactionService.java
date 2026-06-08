package org.loyaltyengine.points_service.modules.transactions.services;

import org.loyaltyengine.points_service.modules.transactions.dtos.CreatePointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.dtos.PointTransactionDto;

public interface PointTransactionService {
    public PointTransactionDto createPointTransaction(CreatePointTransactionDto pointTransactionDto);
}
