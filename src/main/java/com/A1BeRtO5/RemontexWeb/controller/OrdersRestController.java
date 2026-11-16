package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor// конструктор лише для нотналів і файнелів
@CrossOrigin(origins = "*") // Каже браузеру: "Дозволь сайтам з інших портів (наприклад, з Live Server) брати дані з цього бекенду"

public class OrdersRestController {
    private final OrdersService ordersService;  // ioc

    @PostMapping("/api/orders")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        ordersService.saveOrder(order);
        return ResponseEntity.ok("Замовлення створено");
    }
    @GetMapping("/api/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> listAllOrders = ordersService.getAllOrders();
        return ResponseEntity.ok(listAllOrders);
    }
    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable Long id) {
        ordersService.deleteOrdersByID(id);
        return ResponseEntity.ok("Замовлення видалено");
    }
}