package com.example.awesomepizza.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "AwesomePizza")
public class AwesomePizzaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "orderStatus")
    private String orderStatus;

    @Column(name = "pizzaType")
    private String pizzaType;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "orderDate")
    private Date orderDate;

}
