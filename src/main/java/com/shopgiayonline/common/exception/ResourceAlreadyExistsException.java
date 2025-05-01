package com.shopgiayonline.common.exception;

/**
 * Dùng khi phát hiện tài nguyên (User, Email, Phone, ...) đã tồn tại
 * → sẽ được GlobalExceptionHandler bắt và trả về HTTP 409 Conflict.
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}