package com.retail.ordering.system.orderservice.service;

import com.retail.ordering.system.orderservice.dto.cart.CartDto;
import com.retail.ordering.system.orderservice.dto.report.ProductResponse;
import com.retail.ordering.system.orderservice.entities.Order;
import com.retail.ordering.system.orderservice.entities.OrderItem;
import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.exception.OrderNotFoundException;
import com.retail.ordering.system.orderservice.repository.OrderItemsRepository;
import com.retail.ordering.system.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;

    public OrderService(CartService cartService, OrderRepository orderRepository, OrderItemsRepository orderItemsRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    public void placeOrder(User user) {

        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(user);
        // create the order and save it
        Order newOrder=Order.builder()
                .createdDate(LocalDate.now())
                .user(user)
                .totalPrice(cartDto.getTotalCost()).build();
        orderRepository.save(newOrder);

        cartDto.getcartItems().stream()
                .map(cartItemDto->{
                    OrderItem orderItem =OrderItem.builder()
                                        .createdDate(LocalTime.now())
                                        .price(cartItemDto.getPrice())
                                        .productId(cartItemDto.getId())
                                        .quantity(cartItemDto.getQuantity())
                                        .order(newOrder)
                                        .build();
                    return orderItemsRepository.save(orderItem);
                });
        cartService.deleteUserCartItems(user);
    }

    public Order getOrder(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseThrow( ()-> new OrderNotFoundException("Order not found"));
    }

    public List<ProductResponse> getProductList(String reportType) {

        orderRepository.findAll()
                .stream()
                .filter(order -> order.getCreatedDate().isEqual(LocalDate.now()))
                .limit(5)
                .collect(Collectors.toList());

        return null;
    }

    public List<ProductResponse> getSaleSummaryPerDay(LocalDate fromDate, LocalDate toDate) {
        return null;
    }
}


