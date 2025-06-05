package com.example.awesomepizza.controller;

import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import com.example.awesomepizza.service.IAwesomePizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customerController")
@RestController
public class CustomerController {

    @Autowired
    IAwesomePizzaService AwesomePizzaService;

    @PostMapping("/createOrder")
    public CreateOrderResponse createOrder(
            @RequestBody CreateOrderDto createOrderDto
    ) {
        return AwesomePizzaService.createOrder(createOrderDto);
    }

    @GetMapping("/orderStatus")
    public String orderStatus(
            @RequestParam(name = "orderId") String orderId
    ) {
        return AwesomePizzaService.orderStatus(orderId);
    }

}
