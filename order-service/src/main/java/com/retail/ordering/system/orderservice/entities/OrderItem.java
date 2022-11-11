package com.retail.ordering.system.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "price")
    private @NotNull double price;

    @Column(name = "created_date")
    private LocalTime createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_code")
    private String productCode;

}
