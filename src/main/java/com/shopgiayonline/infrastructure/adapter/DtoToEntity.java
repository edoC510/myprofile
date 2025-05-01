package com.shopgiayonline.infrastructure.adapter;

public interface DtoToEntity<ENTITY> {
    ENTITY dtoToEntity(ENTITY e);
}
