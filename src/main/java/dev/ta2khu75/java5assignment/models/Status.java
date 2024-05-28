package dev.ta2khu75.java5assignment.models;

public enum Status {
    PENDING, // Đang chờ xử lý
    PROCESSING, // Đang xử lý
    SHIPPING, // Đang giao
    SHIPPED, // Đã giao hàng
    DELIVERED, // Đã nhận hàng
    CANCELED // Đã hủy
}
