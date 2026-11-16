package com.A1BeRtO5.RemontexWeb.service;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    public void saveOrder(Orders order) {
        ordersRepository.save(order);
    }
    public List<Orders> getAllOrders () {
        return ordersRepository.findAll();
    }
    public void deleteOrdersByID(Long id) {
        ordersRepository.deleteById(id);
    }
}