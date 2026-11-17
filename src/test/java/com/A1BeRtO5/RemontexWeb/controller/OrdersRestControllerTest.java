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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    @DisplayName("тест читання всіх замовлень")
    @Test
    void getAllOrdersTest() {
        List<Orders> listAll = List.of(new Orders());
        when(ordersRepository.findAll()).thenReturn(listAll);

        ordersService.getAllOrders();
        verify(ordersRepository, times(1)).findAll();
    }
    @DisplayName("тест видалення замовлення")
    @Test
    void deleteOrdersByIDTest() {
        Long id  = 1L;
        ordersService.deleteOrdersByID(id);
        verify(ordersRepository, times(1)).deleteById(id);
    }
    @DisplayName("тест пошуку замовлення по номеру")
    @Test
    void searchOrdersTest() {
        String phone = "+380501234567";
        List<Orders> orderlist = new ArrayList<>(List.of());
        when(ordersRepository.findByPhoneNumber(phone)).thenReturn(orderlist);
        ordersService.searchOrderWithPhoneNumber(phone);
        verify(ordersRepository, times(1)).findByPhoneNumber(phone);
    }
}
