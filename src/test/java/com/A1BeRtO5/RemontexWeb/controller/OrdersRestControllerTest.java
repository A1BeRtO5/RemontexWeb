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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("тест оновлення даних замовлення")
    @Test
    void updateOrderTest() {
        Long id = 1L;
        Orders order = new Orders();
        when(ordersRepository.findById(id)).thenReturn(Optional.of(order));

        ordersService.updateOrder(id, order);
        //Перевіряємо чи запустився метод save один раз
        verify(ordersRepository, times(1)).save(order);
    }
    @DisplayName("тест оновлення замовлення з хибним id")
    @Test
    void updateOrderTestWithWrongID() {
        Long id = 1L;
        Orders order = new Orders();
        when(ordersRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class ,()-> ordersService.updateOrder(id, order));

        //Перевіряємо чи запустився метод save не запускався
        verify(ordersRepository, never()).save(order);
    }
}
