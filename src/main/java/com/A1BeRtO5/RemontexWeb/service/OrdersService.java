package com.A1BeRtO5.RemontexWeb.service;

import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
}
