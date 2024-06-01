package dev.ta2khu75.java5assignment.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.repositories.OrderDetailsRepository;
import dev.ta2khu75.java5assignment.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository repository;
    @Override
    public OrderDetails createOrderDetails(Order order, Product product, Integer quantity) {
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setQuantity(quantity);
        return repository.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override   
    public List<Object[]> countProduct() {
        return repository.countProduct();
    }

    @Override
    public List<Object[]> sumPriceGroupByCateogry() {
        return repository.sumPriceGroupByCategory();
    }

}
