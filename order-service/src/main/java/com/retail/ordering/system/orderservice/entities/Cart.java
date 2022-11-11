package com.retail.ordering.system.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalTime createdDate;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_desc")
    private String productDescription;

    @Column(name = "product_price")
    private @NotNull double productPrice;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private int quantity;




}

