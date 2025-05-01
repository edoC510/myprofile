package com.shopgiayonline.entity;

import java.util.List;

import com.shopgiayonline.common.enums.AddressType;
import com.shopgiayonline.entity.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {

    @Column(name = "address_line", nullable = false, length = 1000)
    private String addressLine;

    @Column(name = "ward_id", length = 50)
    private String wardId;

    @Column(name = "district_id", length = 50)
    private String districtId;

    @Column(name = "province_id", length = 50)
    private String provinceId;

    @Column(name = "ward_name", length = 255)
    private String wardName;

    @Column(name = "district_name", length = 255)
    private String districtName;

    @Column(name = "province_name", length = 255)
    private String provinceName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType = AddressType.HOME;

    @Builder.Default
    @Column(name = "is_default")
    private Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;
}