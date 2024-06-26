package dev.ta2khu75.java5assignment.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.models.Status;
import dev.ta2khu75.java5assignment.services.OrderDetailsService;
import dev.ta2khu75.java5assignment.services.OrderService;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;
    private final OrderService service;

    @GetMapping("cancel")
    public String getMethodName(@RequestParam Long id) throws IOException {
        Order order = service.getOrderById(id);
        order.setStatus(Status.CANCELED);
        for (OrderDetails orderDetails : order.getOrderDetails()) {
            Product product = orderDetails.getProduct();
            product.setNumberOfSales(product.getNumberOfSales() - orderDetails.getQuantity());
            product.setQuantity(product.getQuantity() + orderDetails.getQuantity());
            productService.updateProduct(product, null);
        }
        service.updateOrder(order);
        return "redirect:/profile";
    }

    @GetMapping("track")
    public String getMethodName(@RequestParam Long id, Model model) {
        model.addAttribute("order", service.getOrderById(id));
        model.addAttribute("orderDetails", orderDetailsService.getOrderDetailsByOrderId(id));
        model.addAttribute("page", "order-details");
        return "index";
    }
}
