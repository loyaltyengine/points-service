package org.loyaltyengine.points_service.modules.points.models;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.loyaltyengine.points_service.modules.points.enums.PointStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "points")
@EntityListeners(AuditingEntityListener.class)
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pointId;

    @Column(nullable = false, unique = true)
    private String propertyId;

    @Column(nullable = false, updatable = false)
    private String customerId;

    @Column(nullable = false, updatable = false)
    private String pointTypeId;

    @Column(nullable = false)
    private Integer remainingPoints;

    @Column(nullable = false, updatable = false)
    private Integer numberOfPoints;

    @Column(nullable = false)
    private Boolean exchangeable;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointStatus status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;
    private OffsetDateTime validFrom;
    private OffsetDateTime expireAt;

}
