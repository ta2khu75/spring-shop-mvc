package dev.ta2khu75.java5assignment.services;

import java.util.List;

import dev.ta2khu75.java5assignment.models.OrderDetails;

public interface TelegramService {
    public void sendList();
    public void addToQueue(List<OrderDetails> listOrderDetails);
    public void send();
}
