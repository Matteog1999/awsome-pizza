package com.example.awesomepizza.service;

import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface IAwesomePizzaService {

    CreateOrderResponse createOrder(CreateOrderDto createOrderDto) throws BadRequestException;

    String orderStatus(String orderId) throws BadRequestException;

    List<CreateOrderResponse> allOrders();

    Boolean updateStatus(String orderId, String status) throws BadRequestException;
}
