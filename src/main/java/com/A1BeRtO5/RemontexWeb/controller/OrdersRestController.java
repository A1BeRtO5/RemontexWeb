package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.entity.HomePageStatusPanel;
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
    @GetMapping("/api/orders/search")
    public ResponseEntity<List<Orders>> searchOrders(@RequestParam("phone") String phoneNumber) {
        List <Orders> searchedOrders = ordersService.searchOrderWithPhoneNumber(phoneNumber);
        return ResponseEntity.ok(searchedOrders);
    }
    @PatchMapping("/api/orders/{id}")
    public ResponseEntity<Orders>updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {
        Orders savedOrder = ordersService.updateOrder(id , updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }
    @GetMapping("/api/orders/stats")
    public ResponseEntity<HomePageStatusPanel> getOrderStatus() {
        HomePageStatusPanel status = ordersService.getAllStatus();
        return ResponseEntity.ok(status);
    }
}