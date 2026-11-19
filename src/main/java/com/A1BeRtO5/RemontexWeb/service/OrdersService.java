package com.A1BeRtO5.RemontexWeb.service;

import com.A1BeRtO5.RemontexWeb.entity.OrderStatus;
import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<Orders> searchOrderWithPhoneNumber (String phoneNumber) {
        return ordersRepository.findByPhoneNumber(phoneNumber);
    }
    public Orders updateOrder(Long id, Orders newOrders) {
        Orders changeOrder = ordersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Замовлення з ID " + id + " не знайдено"));

        changeOrder.setPhoneNumber(newOrders.getPhoneNumber());
        changeOrder.setSerialNumber(newOrders.getSerialNumber());
        changeOrder.setClient(newOrders.getClient());
        changeOrder.setRegistrationDate(newOrders.getRegistrationDate());
        changeOrder.setOrdersGroup(newOrders.getOrdersGroup());
        changeOrder.setPrice(newOrders.getPrice());
        changeOrder.setStatus(newOrders.getStatus());
        changeOrder.setColor(newOrders.getColor());

        return ordersRepository.save(changeOrder);
    }
}
