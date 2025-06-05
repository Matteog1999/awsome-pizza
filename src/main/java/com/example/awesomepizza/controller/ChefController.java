package com.example.awesomepizza.controller;

import com.example.awesomepizza.model.response.CreateOrderResponse;
import com.example.awesomepizza.service.IAwesomePizzaService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("chefController")
@RestController
public class ChefController {

    @Autowired
    private IAwesomePizzaService iAwesomePizzaService;

    @GetMapping("/allOrders")
    public List<CreateOrderResponse> allOrders() {
        return iAwesomePizzaService.allOrders();
    }

    @PatchMapping("/updateStatus")
    public Boolean orderStatus(
            @RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "status") String status
    ) throws BadRequestException {
       return iAwesomePizzaService.updateStatus(orderId,status);
    }

}
