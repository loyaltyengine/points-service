package org.loyaltyengine.points_service.modules.templates.models;

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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "point_templates")
@EntityListeners(AuditingEntityListener.class)
public class PointTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pointTemplateId;

    @Column(nullable = false, updatable = false)
    private String propertyId;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer numberOfPoints;

    private Integer validNumberOfDays;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;
}
