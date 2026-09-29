package org.loyaltyengine.points_service.modules.redemptions.models;


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
import org.loyaltyengine.points_service.modules.redemptions.utils.RedemptionType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "redemptions")
@EntityListeners(AuditingEntityListener.class)
public class Redemption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String redemptionId;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private RedemptionType redemptionType;

    @Column( nullable = false)
    private String redemptionRuleId;
    private String couponCode;
    private String propertyId;
    private String customerId;
    private Integer numberOfPoints;
    private BigDecimal calculatedValue;
    private Integer totalRemainingPoints;
    private Integer totalDebitedPoints;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private OffsetDateTime createdAt;
}
