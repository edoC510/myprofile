package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.util.List;

import com.shopgiayonline.common.enums.WeightUnit;
import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weights", uniqueConstraints = @UniqueConstraint(columnNames = { "value", "unit" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeightProduct extends BaseEntity {
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeightUnit unit;

    @Builder.Default
    private Short status = 1; // 0: Deleted, 1: Active

    @OneToMany(mappedBy = "weight")
    private List<ProductVariant> productVariants;
}