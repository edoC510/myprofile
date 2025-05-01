package com.shopgiayonline.common.exception;

/**
 * Dùng khi validate đầu vào thất bại ở mức business logic
 * (ví dụ: password và confirmPassword không khớp).
 * → sẽ được GlobalExceptionHandler bắt và trả về HTTP 400 Bad Request.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}