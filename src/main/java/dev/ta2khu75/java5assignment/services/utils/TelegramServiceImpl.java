package dev.ta2khu75.java5assignment.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.services.TelegramService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {
    private final TelegramClient telegramClient;
    private final List<List<OrderDetails>> supListOrderDetails = new ArrayList<>();
    private final List<OrderDetails> subListOrderDetails = new ArrayList<>();

    @Override
    @Scheduled(fixedDelay = 1000 * 5)
    public void sendList() {
        while (subListOrderDetails.isEmpty() && !supListOrderDetails.isEmpty()) {
            subListOrderDetails.addAll(supListOrderDetails.remove(0));
            Order order = subListOrderDetails.get(0).getOrder();
            SendMessage sendMessage = SendMessage.builder().chatId(5394722994L)
                    .text(String.format("%s Order %d %s %d", order.getStatus().toString(), order.getId(),
                            order.getFullName(), order.getTotal()))
                    .build();
            try {
                telegramClient.execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addToQueue(List<OrderDetails> listOrderDetails) {
        this.supListOrderDetails.add(listOrderDetails);
    }

    @Override
    @Scheduled(fixedDelay = 2000)
    public void send() {
        while (!subListOrderDetails.isEmpty()) {
            OrderDetails orderDetails = subListOrderDetails.remove(0);
            String caption = "Product Name : " + orderDetails.getProduct().getName() + "\n"
                    + "Quantity :" + orderDetails.getQuantity() + "\n"
                    + "Price :" + orderDetails.getProduct().getPrice() + "\n";
            SendPhoto sendPhoto = SendPhoto.builder().chatId(5394722994L)
                    .photo(new InputFile(orderDetails.getProduct().getImageUrl())).caption(caption).build();
            try {
                telegramClient.execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

}
