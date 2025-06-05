package com.example.awesomepizza;

import com.example.awesomepizza.exception.AlreadyPendingOrderException;
import com.example.awesomepizza.exception.NoOrdersFoundException;
import com.example.awesomepizza.exception.OrderNotFoundException;
import com.example.awesomepizza.mapper.AwesomePizzaMapper;
import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import com.example.awesomepizza.model.response.CreateOrderResponse;
import com.example.awesomepizza.repository.AwesomePizzaRepository;
import com.example.awesomepizza.service.impl.AwesomePizzaServiceImpl;
import com.example.awesomepizza.utils.Constants;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class AwsomePizzaServiceTest {

    @Mock
    private AwesomePizzaRepository awesomePizzaRepository;

    @InjectMocks
    private AwesomePizzaServiceImpl awesomePizzaService;

    @Test
    void getOrderById_shouldReturnOrder_ifExists() throws BadRequestException {
            final String EXPECTED_RESULT = "123";
        Mockito.when(awesomePizzaRepository.findByOrderId(anyString())).thenReturn(Optional.of(EXPECTED_RESULT));

        assertEquals(EXPECTED_RESULT, awesomePizzaService.orderStatus("123"));
    }

    @Test
    void orderStatus_shouldThrowException_ifNotFound() {
        assertThrows(OrderNotFoundException.class, () -> awesomePizzaService.orderStatus("999"));
    }

    @Test
    void orderStatus_shouldThrowException_BadRequest() {
        assertThrows(BadRequestException.class, () -> awesomePizzaService.orderStatus(null));
    }

    @Test
    void updateStatus_shouldReturnOrder_ifExists() throws BadRequestException {
        Mockito.when(awesomePizzaRepository.findProcessing()).thenReturn(null);
        Mockito.when(awesomePizzaRepository.updateStatus(anyString(),anyString())).thenReturn(1);

        assertEquals(true, awesomePizzaService.updateStatus("123", Constants.ORDER_COMPLETED));
    }

    @Test
    void updateStatus_shouldThrowException_AlreadyPendingOrderException() {
        Mockito.when(awesomePizzaRepository.findProcessing()).thenReturn(new AwesomePizzaEntity());

        assertThrows(AlreadyPendingOrderException.class, () -> awesomePizzaService.updateStatus("123", Constants.ORDER_PROCESSING));
    }

    @Test
    void updateStatus_shouldThrowException_BadRequestException() {
        assertThrows(BadRequestException.class, () -> awesomePizzaService.updateStatus("null", null));
    }

    @Test
    void allOrders_shouldThrowException_NoOrdersFoundException() {
        Mockito.when(awesomePizzaRepository.findByOrderByOrderDateAsc()).thenReturn(null);

        assertThrows(NoOrdersFoundException.class, () -> awesomePizzaService.allOrders());
    }

    @Test
    void allOrders_shouldReturnAllOrders_ifExists() {
        List<AwesomePizzaEntity> awesomePizzaEntities = new ArrayList<>();
        AwesomePizzaEntity awesomePizzaEntity = new AwesomePizzaEntity();
        awesomePizzaEntity.setPizzaType("Margherita");
        awesomePizzaEntity.setOrderId("123");
        awesomePizzaEntity.setId(1L);
        awesomePizzaEntity.setAddress("Via Test");
        awesomePizzaEntity.setOrderDate(new Date());
        awesomePizzaEntity.setOrderStatus(Constants.ORDER_PROCESSING);
        awesomePizzaEntities.add(awesomePizzaEntity);


        Mockito.when(awesomePizzaRepository.findByOrderByOrderDateAsc()).thenReturn(awesomePizzaEntities);

        List<CreateOrderResponse> createOrderResponses = awesomePizzaService.allOrders();

        assertNotNull(createOrderResponses);
        assertEquals(1, createOrderResponses.size());
        assertEquals(1L, createOrderResponses.get(0).getId());
        assertEquals(Constants.ORDER_PROCESSING, createOrderResponses.get(0).getOrderStatus());
    }
}
