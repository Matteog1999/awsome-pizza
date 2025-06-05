package com.example.awesomepizza.service.impl;

import com.example.awesomepizza.mapper.AwesomePizzaMapper;
import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import com.example.awesomepizza.repository.AwesomePizzaRepository;
import com.example.awesomepizza.service.IAwesomePizzaService;
import com.example.awesomepizza.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AwesomePizzaServiceImpl implements IAwesomePizzaService {

    @Autowired
    AwesomePizzaRepository awesomePizzaRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderDto createOrderDto) {
        return AwesomePizzaMapper.INSTANCE.toDto(awesomePizzaRepository.saveAndFlush(this.createEntity(createOrderDto)));
    }

    @Override
    public String orderStatus(String orderId) {
        return awesomePizzaRepository.findByOrderId(orderId);
    }

    @Override
    public List<CreateOrderResponse> allOrders() {
        return AwesomePizzaMapper.INSTANCE.toDtos(awesomePizzaRepository.findByOrderByOrderDateAsc());
    }

    @Transactional
    @Override
    public Boolean updateStatus(String orderId, String status) {
        boolean result = false;
        String mappedStatus = this.mapStatus(status);
        if (null != mappedStatus && null != orderId) {
            if (null == awesomePizzaRepository.findProcessing() || !mappedStatus.equals(Constants.ORDER_PROCESSING)) {
                Integer updateStatus = awesomePizzaRepository.updateStatus(orderId, mappedStatus);
                return this.mapUpdateResult(updateStatus);
            }
        }
        return result;
    }

    private Boolean mapUpdateResult(Integer updateResult) {
        boolean result = false;
        if (updateResult == 1) {
            return true;
        }
        return result;
    }

    private String mapStatus(String status) {
        String mappedStatus = "";

        switch (status) {
            case "completed":
                mappedStatus = Constants.ORDER_COMPLETED;
                break;
            case "processing":
                mappedStatus = Constants.ORDER_PROCESSING;
                break;
            default:
                break;
        }
        return mappedStatus;
    }

    private AwesomePizzaEntity createEntity(CreateOrderDto createOrderDto) {
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
