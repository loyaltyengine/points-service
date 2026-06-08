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
import org.loyaltyengine.points_service.modules.redemptions.utils.CouponType;
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
@Table(name = "redemption_rules")
@EntityListeners(AuditingEntityListener.class)
public class RedemptionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String redemptionRuleId;

    @Column(nullable = false)
    private String propertyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RedemptionType redemptionType;

    @Enumerated(EnumType.STRING)
    private CouponType couponType; // For COUPON redemption type

    @Column(nullable = false)
    private Integer pointsRequired;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal equivalentValue;

    private String currency;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private OffsetDateTime updatedAt;
}
