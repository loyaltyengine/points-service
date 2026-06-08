package org.loyaltyengine.points_service.modules.transactions.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.loyaltyengine.points_service.shared.enums.TransactionReason;
import org.loyaltyengine.points_service.shared.enums.TransactionType;

import java.time.OffsetDateTime;

@Setter
@Getter
@Builder
public class PointTransactionDto {
    private String transactionId;
    private String propertyId;
    private String customerId;
    private String pointId;
    private Integer amount;
    private TransactionType type;
    private TransactionReason reason;
    private OffsetDateTime createdAt;
}
