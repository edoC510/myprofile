package com.shopgiayonline.common.status;

public class NotificationType {
    public static final Short PAYMENT = 0; // kh gửi thông báo xác nhận đơn hàng đến ADMIN
    public static final Short CONFIRM_ORDER = 1; // admin gửi thông báo xác nhận đơn hàng đến KHACH HANG
    public static final Short ADMIN_CANCEL_ORDER = 2; // admin gửi thông báo huy đơn hàng đến KhachHang
    public static final Short RETURN_ORDER = 3; // kh gửi thông báo yeu cau doi tra đơn hàng đến ADMIN
    public static final Short CONFIRM_RETURN_ORDER = 4; // admin gửi thông báo xác nhận đơn hàng đến KHACH HANG
    public static final Short CANCEL_RETURN_ORDER = 5; // admin gửi thông báo huy doi tra đơn hàng đến KHACH HANG
    public static final Short CUSTOMER_CANCEL_ORDER = 6; // kh gửi thông báo huy đơn hàng đến admin
    public static final Short RETURN_COMPLETE = 7; // admin gửi tb đến khách
    public static final Short COMPLETE = 8; // admin gửi tb đến khách
}
