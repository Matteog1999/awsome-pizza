package com.example.awesomepizza.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {

    private Long id;

    private String address;

    private String phoneNumber;

    private String orderStatus;

    private String pizzaType;

    private String orderId;

    private Date orderDate;

}
