package com.shopgiayonline.entity;

import java.util.List;
import java.util.UUID;

import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Builder.Default
    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId = UUID.randomUUID();

    @Column(nullable = false, length = 255)
    private String name;

    private String description;

    @Column(name = "main_image", nullable = false, length = 255)
    private String mainImage;

    @Builder.Default
    private Short status = 1; // 0: Deleted, 1: Active

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> productVariants;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
}