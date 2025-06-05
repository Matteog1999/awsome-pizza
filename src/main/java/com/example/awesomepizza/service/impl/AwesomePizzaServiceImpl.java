package com.example.awesomepizza.service.impl;

import com.example.awesomepizza.exception.AlreadyPendingOrderException;
import com.example.awesomepizza.exception.NoOrdersFoundException;
import com.example.awesomepizza.exception.OrderNotFoundException;
import com.example.awesomepizza.mapper.AwesomePizzaMapper;
import com.example.awesomepizza.model.dto.CreateOrderDto;
import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import com.example.awesomepizza.repository.AwesomePizzaRepository;
import com.example.awesomepizza.service.IAwesomePizzaService;
import com.example.awesomepizza.utils.Constants;
import com.example.awesomepizza.utils.UtilsMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AwesomePizzaServiceImpl implements IAwesomePizzaService {

    @Autowired
    private AwesomePizzaRepository awesomePizzaRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderDto createOrderDto) {
        return AwesomePizzaMapper.INSTANCE.toDto(awesomePizzaRepository.saveAndFlush(UtilsMapper.createEntity(createOrderDto)));
    }

    @Override
    public String orderStatus(String orderId) throws BadRequestException,OrderNotFoundException {
        if (orderId == null) {
            throw new BadRequestException();
        }
        Optional<String> orderStatusOpt = awesomePizzaRepository.findByOrderId(orderId);

        if (orderStatusOpt.isEmpty())
            throw new OrderNotFoundException(orderId);

        return orderStatusOpt.get();
    }

    @Override
    public List<CreateOrderResponse> allOrders() {
        List<AwesomePizzaEntity> awesomePizzaEntities = awesomePizzaRepository.findByOrderByOrderDateAsc();

        if (awesomePizzaEntities != null) {
            return AwesomePizzaMapper.INSTANCE.toDtos(awesomePizzaEntities);
        }else {
            throw new NoOrdersFoundException();
        }
    }

    @Transactional
    @Override
    public Boolean updateStatus(String orderId, String status) throws BadRequestException {
        if (null != status && null != orderId) {
            if (null == awesomePizzaRepository.findProcessing() || !status.equals(Constants.ORDER_PROCESSING)) {
                Integer updateStatus = awesomePizzaRepository.updateStatus(orderId, status);
                return UtilsMapper.mapUpdateResult(updateStatus);
            }else{
                throw new AlreadyPendingOrderException();
            }
        }else {
            throw new BadRequestException();
        }
    }

}
