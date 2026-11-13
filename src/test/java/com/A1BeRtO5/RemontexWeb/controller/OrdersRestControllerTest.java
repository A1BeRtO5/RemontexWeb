package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import com.A1BeRtO5.RemontexWeb.service.OrdersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class OrdersRestControllerTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    @DisplayName("Тест запису замовлення")
    @Test
    void createOrderTest() {
        Orders order = new Orders();
        when(ordersRepository.save(order)).thenReturn(order);

        ordersService.saveOrder(order);
        verify(ordersRepository, times(1)).save(order);
    }
}
