package com.shopgiayonline.common.status;

public class OrderStatus {
    public static final short CANCELLED = 0; // Hủy
    public static final short PENDING = 2; // Chờ xác nhận
    public static final short COMPLETED = 3; // Hoàn thành
    public static final short DELIVERING = 5; // Đang giao hàng
    public static final short RETURNED = 7; // Đổi trả
    public static final short RETURNED_CONFIRM = 8; // Xác nhận đổi trả
    public static final short RETURNED_CANCEL = 9; // Hủy đổi trả

}
