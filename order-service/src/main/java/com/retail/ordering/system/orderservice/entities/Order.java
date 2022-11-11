package com.retail.ordering.system.orderservice.entities;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
