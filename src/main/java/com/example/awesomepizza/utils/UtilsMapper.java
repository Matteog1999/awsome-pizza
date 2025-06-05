package com.example.awesomepizza.utils;

import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


public  class UtilsMapper {

    public static Boolean mapUpdateResult(Integer updateResult) {
        boolean result = false;
        if (updateResult == 1) {
            return true;
        }
        return result;
    }

    public static AwesomePizzaEntity createEntity(CreateOrderDto createOrderDto) {
        AwesomePizzaEntity entity = new AwesomePizzaEntity();
        entity.setAddress(createOrderDto.getAddress());
        entity.setPizzaType(createOrderDto.getPizzaType());
        entity.setOrderDate(new Date());
        entity.setOrderStatus(Constants.ORDER_CREATED);
        entity.setPhoneNumber(createOrderDto.getPhoneNumber());
        entity.setOrderId(UUID.randomUUID().toString());
        return entity;
    }

}
