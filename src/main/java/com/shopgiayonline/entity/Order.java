package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Builder.Default
    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId = UUID.randomUUID();

    @Column(name = "recipient_name", nullable = false, length = 255)
    private String recipientName;

    private String reason;

    private String description;

    @Builder.Default
    @Column(name = "shipping_fee", precision = 15, scale = 2)
    private BigDecimal shippingFee = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "customer_pay_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal customerPayAmount;

    @Column(name = "discounted_amount", precision = 15, scale = 2)
    private BigDecimal discountedAmount;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "shipping_date")
    private LocalDateTime shippingDate;

    @Builder.Default
    private Short status = 2; // 0: Hủy, 2: Chờ xác nhận, 3: Hoàn thành, 5: Đang giao, 7: Đổi trả, 8: Xác nhận
                              // đổi trả, 9: Hủy đổi trả

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
}