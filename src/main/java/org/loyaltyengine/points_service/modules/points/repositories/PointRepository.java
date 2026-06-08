package org.loyaltyengine.points_service.modules.points.repositories;

import jakarta.persistence.LockModeType;
import org.loyaltyengine.points_service.modules.points.models.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, String> {

    public Optional<Point> findByPointIdAndPropertyIdAndCustomerId(String pointId, String propertyId, String customerId);

    public Page<Point> findByPropertyIdAndCustomerId(String propertyId, String customerId, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Point p WHERE p.propertyId = ?1 " +
            "AND p.customerId = ?2" +
            "AND p.remainingPoints > 0 " +
            "AND p.status = 'ACTIVE' " +
            "AND p.expireAt > ?3 " +
            "ORDER BY p.createdAt ASC")
    public List<Point> findActiveOldestByPropertyIdAndCustomerId(
            String propertyId,
            String customerId,
            OffsetDateTime now);

}
