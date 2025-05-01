package com.shopgiayonline.entity;

import java.util.List;

import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    private String name;

    @Builder.Default
    private Short status = 1; // 0: Inactive, 1: Active

    @OneToMany(mappedBy = "paymentMethod")
    private List<Order> orders;
}