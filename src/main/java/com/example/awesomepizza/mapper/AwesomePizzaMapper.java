package com.example.awesomepizza.mapper;

import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwesomePizzaMapper {

    AwesomePizzaMapper INSTANCE = Mappers.getMapper(AwesomePizzaMapper.class);

    CreateOrderResponse toDto(AwesomePizzaEntity AwesomePizzaEntity);

    AwesomePizzaEntity toEntity(CreateOrderResponse createOrderResponse);

    List<CreateOrderResponse> toDtos(List<AwesomePizzaEntity> awesomePizzaEntities);
}
