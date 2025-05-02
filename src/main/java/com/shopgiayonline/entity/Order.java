package com.shopgiayonline.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "recipient_name", length = 255)
    private String recipientName;

    private String reason;

    private String description;

    @Column(name = "shipping_fee", precision = 15, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "total_amount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "customer_pay_amount", precision = 15, scale = 2)
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderDetail> orderDetails;
}