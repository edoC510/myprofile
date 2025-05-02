package com.shopgiayonline.entity;

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
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {

    @Column(nullable = false, length = 10000)
    private String url;

    @Column(name = "image_code")
    private String imageCode;

    @Builder.Default
    private Short status = 1; // 0: Deleted, 1: Active

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}