package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "product_variants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant extends BaseEntity {

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "product_variant_code")
    private String productVariantCode;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "import_price", precision = 15, scale = 2)
    private BigDecimal importPrice;

    @Column(name = "after_promotion_price", precision = 15, scale = 2)
    private BigDecimal afterPromotionPrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Builder.Default
    private Short status = 1; // 0: Out of stock, 1: In stock, 2: Deleted

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "weight_id")
    private WeightProduct weight;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notification> notifications;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CartDetail> cartDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderDetail> orderDetails;
}