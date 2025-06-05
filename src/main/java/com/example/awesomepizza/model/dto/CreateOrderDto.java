package com.example.awesomepizza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateOrderDto {

    private String address;

    private String phoneNumber;

    private String pizzaType;

}
