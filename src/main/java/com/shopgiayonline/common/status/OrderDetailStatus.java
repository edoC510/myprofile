package com.shopgiayonline.common.status;

public class OrderDetailStatus {
    public static final short CANCEL = 0;
    public static final short WAIT_CONFIRM = 2;
    public static final short COMPLETE = 3;
    public static final short PREPARING = 4; // Đang chuẩn bị hàng
    public static final short DELIVERED_TO_CARRIER = 5; // Giao cho đơn vị vận chuyển
    public static final short DELIVERING = 6;
    public static final short RETURN = 7;
    public static final short CONFIRM_RETURN = 8;
    public static final short CANCEL_RETURN = 9;
}
