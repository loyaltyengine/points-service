package org.loyaltyengine.points_service.modules.types.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "point_types")
@EntityListeners(AuditingEntityListener.class)
public class PointType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pointTypeId;

    @Column(nullable = false, updatable = false)
    private String propertyId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer numberOfPoints;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
