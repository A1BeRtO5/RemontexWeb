package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {
    private  final OrdersService ordersService;

    public OrdersRestController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }
    @GetMapping("/{id}") //(api/orders/{id})
    public Orders getOrdersById (@PathVariable Long id) {
        return ordersService.getOrdersById(id);
    }
    @PostMapping
    public String createOrders(@RequestBody Orders order) {
         ordersService.save(order);
         return "Замовлення додано";
    }
}
