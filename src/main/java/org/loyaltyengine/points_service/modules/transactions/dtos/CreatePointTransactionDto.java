package org.loyaltyengine.points_service.modules.transactions.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.loyaltyengine.points_service.shared.enums.TransactionReason;
import org.loyaltyengine.points_service.shared.enums.TransactionType;

@Builder
@Setter
@Getter
public class CreatePointTransactionDto {
    private String pointId;
    private String propertyId;
    private String customerId;
    private Integer amount;
    private TransactionType type;
    private TransactionReason reason;
}
