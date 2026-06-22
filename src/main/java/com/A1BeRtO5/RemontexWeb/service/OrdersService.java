package com.A1BeRtO5.RemontexWeb.service;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    public List<Orders> getAllOrders () {
        return ordersRepository.findAll();
    }
    public Orders getOrdersById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }
    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }

}
