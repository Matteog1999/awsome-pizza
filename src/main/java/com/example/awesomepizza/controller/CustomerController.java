package com.example.awesomepizza.controller;

import com.example.awesomepizza.exception.OrderNotFoundException;
import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.service.IAwesomePizzaService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customerController")
@RestController
public class CustomerController {

    @Autowired
    private IAwesomePizzaService AwesomePizzaService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid CreateOrderDto createOrderDto
    ) throws BadRequestException {

        return new ResponseEntity<>(AwesomePizzaService.createOrder(createOrderDto), HttpStatus.CREATED);
    }

    @GetMapping("/orderStatus")
    public ResponseEntity<?> orderStatus(
            @RequestParam(name = "orderId") String orderId
    ) throws BadRequestException, OrderNotFoundException {
        return new ResponseEntity<>(AwesomePizzaService.orderStatus(orderId), HttpStatus.OK);
    }

}
