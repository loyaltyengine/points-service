package org.loyaltyengine.points_service.modules.transactions.repositories;

import org.loyaltyengine.points_service.modules.transactions.models.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointTransactionRepository extends JpaRepository<PointTransaction, String> {
}
