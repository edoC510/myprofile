package com.shopgiayonline.entity;

import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "images", uniqueConstraints = @UniqueConstraint(columnNames = { "product_variant_id", "url" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {

    @Column(nullable = false)
    private String url;

    @Builder.Default
    @Column(name = "is_main")
    private Boolean isMain = false;

    @Builder.Default
    private Short status = 1; // 0: Deleted, 1: Active

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;
}