package com.example.awesomepizza.service;

import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.response.CreateOrderResponse;

import java.util.List;

public interface IAwesomePizzaService {
    CreateOrderResponse createOrder(CreateOrderDto createOrderDto);

    String orderStatus(String orderId);

    List<CreateOrderResponse> allOrders();

    Boolean updateStatus(String orderId, String status);
}
