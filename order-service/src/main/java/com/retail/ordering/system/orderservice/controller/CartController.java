package com.retail.ordering.system.orderservice.controller;

import com.retail.ordering.system.orderservice.common.OrderResponse;
import com.retail.ordering.system.orderservice.dto.cart.AddToCartDto;
import com.retail.ordering.system.orderservice.dto.cart.CartDto;
import com.retail.ordering.system.orderservice.dto.cart.CartProductResponse;
import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.repository.UserRepository;
import com.retail.ordering.system.orderservice.service.CartService;
import com.retail.ordering.system.orderservice.service.ProductServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cart API", description = " Cart API is used to provide order cart functionality for  Retail ordering System ")
@Slf4j
@RestController
@RequestMapping("/api/order-service/cart")
public class CartController {

    private final CartService cartService;
    private final ProductServiceClient productServiceClient;

    private final UserRepository userRepository;


    public CartController(CartService cartService, ProductServiceClient productServiceClient, UserRepository userRepository) {
        this.cartService = cartService;
        this.productServiceClient = productServiceClient;
        this.userRepository = userRepository;
    }

    @Operation(summary = "All product added in Order Cart")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "OK - All products added in Cart successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @PostMapping
    public ResponseEntity<OrderResponse> addToCart(@RequestBody @Valid AddToCartDto addToCartDto)  {
        CartProductResponse product = productServiceClient.productByCode(addToCartDto.getProductCode());
        cartService.addToCart(addToCartDto, product);
        return new ResponseEntity<>(new OrderResponse(true, "Added to cart"), HttpStatus.CREATED);
    }


    @Operation(summary = "Get product added in Order Cart")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - All products added in Cart retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping
    public ResponseEntity<CartDto> getCartItems(@RequestParam ("userId") String userId) {
        Optional<User> user = userRepository.findById(userId);
        CartDto cartDto = cartService.listCartItems(user.get());
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


    @Operation(summary = "Get product added in Order Cart based on card id ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - All products added in Cart retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })

    public ResponseEntity<OrderResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto) {
        CartProductResponse product = productServiceClient.productByCode(cartDto.getProductCode());
        cartService.updateCartItem(cartDto,product);
        return new ResponseEntity<>(new OrderResponse(true, "Product has been updated"), HttpStatus.OK);
    }


    @Operation(summary = "Delete product added in Order Cart based on card id ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - All products added in Cart deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<OrderResponse> deleteCartItem(@PathVariable("cartItemId") int itemID)  {
        cartService.deleteCartItem(itemID);
        return new ResponseEntity<>(new OrderResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
