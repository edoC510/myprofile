package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail extends BaseEntity {

    @Builder.Default
    @Column(name = "order_detail_id", nullable = false, unique = true)
    private UUID orderDetailId = UUID.randomUUID();

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Builder.Default
    @Column(name = "product_discount", precision = 15, scale = 2)
    private BigDecimal productDiscount = BigDecimal.ZERO;

    private String reason;

    @Builder.Default
    private Short status = 2; // 0: Hủy, 2: Chờ xác nhận, 3: Hoàn thành, 4: Đang chuẩn bị hàng, 5: Giao đơn vị
                              // vận chuyển, 6: Đang giao, 7: Đổi trả, 8: Xác nhận
                              // đổi trả, 9: Hủy đổi trả
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;
}