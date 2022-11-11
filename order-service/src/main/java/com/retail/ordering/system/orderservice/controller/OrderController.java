package com.retail.ordering.system.orderservice.controller;


import com.retail.ordering.system.orderservice.common.OrderResponse;
import com.retail.ordering.system.orderservice.dto.report.ProductResponse;
import com.retail.ordering.system.orderservice.entities.Order;
import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.repository.UserRepository;
import com.retail.ordering.system.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Order Service API to place Order ", description = " Order Service API is used to place Order for Retail ordering System ")
@RestController
@RequestMapping("/order-service/order")
public class OrderController {

    private final OrderService orderService;

    private final UserRepository userRepository;


    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Place order in ordering system")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "OK - Order is placed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @PostMapping("/add")
    public ResponseEntity<OrderResponse> placeOrder(@RequestParam ("userId") String userId) {

        Optional<User> user = userRepository.findById(userId);
        orderService.placeOrder(user.get());
        return new ResponseEntity<>(new OrderResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @Operation(summary = "Get order details by Order id in ordering system")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "OK - Order is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id){
        Order order = orderService.getOrder(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @Operation(summary = "Get Product details by Order id in for Reporting Purpose")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "OK - Product details by report Type is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/summary")
    public ResponseEntity<List<ProductResponse>> getProductList(@RequestParam ("reportType") String reportType) {

        List<ProductResponse> productList=orderService.getProductList(reportType);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @Operation(summary = "Get Sale report for Reporting Purpose")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "OK -Report Type is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/summary/sale")
    public ResponseEntity<List<ProductResponse>> getSaleSummaryPerDay(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate,
                                                                @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate ToDate) {

        List<ProductResponse> productList=orderService.getSaleSummaryPerDay(fromDate,ToDate);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }


}
