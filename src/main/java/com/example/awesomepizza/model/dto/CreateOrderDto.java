package com.example.awesomepizza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateOrderDto {

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String pizzaType;

}
