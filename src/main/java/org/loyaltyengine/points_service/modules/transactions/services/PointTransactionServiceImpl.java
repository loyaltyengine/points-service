package org.loyaltyengine.points_service.modules.transactions.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.loyaltyengine.points_service.modules.transactions.dtos.CreatePointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.dtos.PointTransactionDto;
import org.loyaltyengine.points_service.modules.transactions.mappers.PointTransactionMapper;
import org.loyaltyengine.points_service.modules.transactions.models.PointTransaction;
import org.loyaltyengine.points_service.modules.transactions.repositories.PointTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointTransactionServiceImpl implements PointTransactionService {
    private final PointTransactionRepository repository;
    private final PointTransactionMapper mapper;

    @Override
    public PointTransactionDto createPointTransaction(CreatePointTransactionDto pointTransactionDto) {
        log.info("Creating point transaction: {}", pointTransactionDto);
        PointTransaction pointTransaction = mapper.toEntity(pointTransactionDto);
        pointTransaction.setCreatedAt(OffsetDateTime.now());

        // Save the point transaction
        PointTransaction saved = repository.save(pointTransaction);

        return mapper.toDto(saved);
    }
}
