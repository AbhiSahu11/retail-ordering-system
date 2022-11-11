package com.retail.ordering.system.orderservice.service;

import com.retail.ordering.system.orderservice.dto.cart.AddToCartDto;
import com.retail.ordering.system.orderservice.dto.cart.CartDto;
import com.retail.ordering.system.orderservice.dto.cart.CartItemDto;
import com.retail.ordering.system.orderservice.dto.cart.CartProductResponse;
import com.retail.ordering.system.orderservice.entities.Cart;
import com.retail.ordering.system.orderservice.entities.User;
import com.retail.ordering.system.orderservice.exception.CartItemNotExistException;
import com.retail.ordering.system.orderservice.repository.CartRepository;
import com.retail.ordering.system.orderservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, CartProductResponse cartProductResponse){
        Cart cart =Cart.builder()
                .productCode(cartProductResponse.getProductCode())
                .productDescription(cartProductResponse.getProductDescription())
                .quantity(addToCartDto.getQuantity())
                .user(userRepository.findById(addToCartDto.getUserId()).get())
                .createdDate(LocalTime.now())
                .build();
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<CartItemDto> cartItems=  cartRepository.findAllByUserOrderByCreatedDateDesc(user).
                                                    stream()
                                                    .map(cart-> new CartItemDto(cart.getId(),cart.getQuantity()))
                                                    .collect(Collectors.toList());
        double totalCost= cartRepository.findAllByUserOrderByCreatedDateDesc(user)
                            .stream()
                            .mapToDouble(cart -> cart.getQuantity() * cart.getProductPrice())
                            .sum();
        return CartDto.builder()
                        .cartItems(cartItems)
                        .totalCost(totalCost)
                        .build();
    }

    public void updateCartItem(AddToCartDto cartDto,CartProductResponse product){
        cartRepository.findById(cartDto.getId()).stream()
                .map(cart -> {
                    cart.setQuantity(cartDto.getQuantity());
                    cart.setCreatedDate(LocalTime.now());
                    cart.setProductCode(product.getProductCode());
                    return cartRepository.save(cart);
                });
    }

    public void deleteCartItem(int id) throws CartItemNotExistException {
        cartRepository.delete(cartRepository.findById(id)
                .orElseThrow(() -> new CartItemNotExistException(String.format("Can't found Cart by ID %s", id))));
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}


