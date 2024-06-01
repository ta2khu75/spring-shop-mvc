package dev.ta2khu75.java5assignment.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.PaymentMethod;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.CartService;
import dev.ta2khu75.java5assignment.services.MailerService;
import dev.ta2khu75.java5assignment.services.OrderDetailsService;
import dev.ta2khu75.java5assignment.services.OrderService;
import dev.ta2khu75.java5assignment.services.ProductService;
import dev.ta2khu75.java5assignment.services.TelegramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class Checkout {
    private final ProductService productService;
    private final CartService service;
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final TelegramService telegramService;
    private final MailerService mailerService;

    @RequestMapping("checkout")
    public String requestMethodName(@SessionAttribute("user") UserResp user, @ModelAttribute Order order, Model model)
            throws JsonProcessingException{
        Map<Long, Integer> map = service.getCart(user.getId());
        if (map == null) {
            return "redirect:/";
        }
        Map<Product, Integer> cart = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            cart.put(productService.getProductById(entry.getKey()), entry.getValue());
        }
        model.addAttribute("carts", cart);
        return "index";
    }

    @ModelAttribute("total")
    public long getTotal(@SessionAttribute("user") UserResp user)
            throws JsonProcessingException{
        long total = 0;
        Map<Long, Integer> map = service.getCart(user.getId());
        if (map == null) {
            return 0;
        }
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            total += productService.getProductById(entry.getKey()).getPrice() * entry.getValue();
        }
        return total;
    }

    @PostMapping("order")
    public String postMethodName(@SessionAttribute("user") UserResp user, Model model,
            @Valid @ModelAttribute Order order,
            BindingResult bindingResult) throws IOException{
        if (bindingResult.hasErrors()) {
            return "index";
        }
        long amount = 0;
        order = orderService.createOrder(user.getId(), order);
        Map<Long, Integer> map = service.getCart(user.getId());
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            OrderDetails orderDetails = orderDetailsService.createOrderDetails(order, product, entry.getValue());
            orderDetailsList.add(orderDetails);
            //tinh tong tien order
            product.setNumberOfSales(product.getNumberOfSales() + orderDetails.getQuantity());
            product.setQuantity(product.getQuantity() - orderDetails.getQuantity());
            if (product.getQuantity() <= 0) {
                product.setActive(false);
            }
            productService.updateProduct(product, null);
            amount += orderDetails.getProduct().getPrice() * orderDetails.getQuantity();
        }
        order.setTotal(amount);
        order = orderService.updateOrder(order);
        service.clearCart(user.getId());
        telegramService.addToQueue(orderDetailsList);
        addToEmail(orderDetailsList, order);
        if (order.getPaymentMethod().equals(PaymentMethod.COD)) {
            model.addAttribute("page", "success");
            return "index";
        } else {
            return String.format("redirect:/payment/%d?amount=%d", order.getId(), amount);
        }
    }

    @ModelAttribute("page")
    public String page() {
        return "checkout";
    }

    private void addToEmail(List<OrderDetails> list, Order order) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder body = new StringBuilder(
                "<table border=\"2px\" style=\"width: 100%\"><thead><tr><th>Name</th><th>image</th><th>Quantity</th><th>Price</th><th>Total</th></tr></thead><tbody>");
        for (OrderDetails orderDetails : list) {
            Product product = orderDetails.getProduct();
            body.append(String.format(
                    "<tr><td align=\"center\">%s</td><td align=\"center\"><img width=\"150px\" src=\"%s\"</td><td align=\"center\">%d</td><td align=\"center\">%d</td></tr></tr>",
                    product.getName(), product.getImageUrl(), orderDetails.getQuantity(), product.getPrice()));
        }
        body.append(" </tbody></table>");
        body.append("<br><h3>Total: " + order.getTotal() + "</h3>");
        mailerService.queue(order.getUser().getEmail(),
                String.format("Shop Tàu khựa đã nhận đơn hàng %d của bạn", order.getId()), body.toString());
    }
}
