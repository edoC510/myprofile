package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.shopgiayonline.common.enums.DiscountType;
import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion extends BaseEntity {

    @Builder.Default
    @Column(name = "promotion_id", nullable = false, unique = true)
    private UUID promotionId = UUID.randomUUID();

    @Column(nullable = false, length = 255)
    private String name;

    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "condition_amount", precision = 15, scale = 2)
    private BigDecimal conditionAmount;

    @Column(name = "discount_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "max_discount", precision = 15, scale = 2)
    private BigDecimal maxDiscount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType;

    @Builder.Default
    private Short status = 1; // 0: Expired, 1: Active, 2: Pending, 3: Completed, 4: Canceled

    @OneToMany(mappedBy = "promotion")
    private List<ProductVariant> productVariants;
}