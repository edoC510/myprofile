package com.shopgiayonline.infrastructure.adapter;

public interface EntityToDto<Entity, DTO> {
    public DTO changeToDto(Entity entity);
}