package com.retail.ordering.system.orderservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String password;

    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orders;


}
