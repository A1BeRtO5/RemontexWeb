package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.entity.OrderStatusStatistics;
import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Каже браузеру: "Дозволь сайтам з інших портів (наприклад, з Live Server) брати дані з цього бекенду"
@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrdersService ordersService;

    // 1. Створення (Тут у тебе все було супер!)
    @PostMapping
    public ResponseEntity<String> createOrder( @RequestBody Orders order){
        ordersService.saveOrder(order);
        return ResponseEntity.ok("Замовлення створено");
    }

    // 2. Отримання всіх
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> ordersList = ordersService.getAllOrders();
        return ResponseEntity.ok(ordersList);
    }

    // 3. Видалення (Дістаємо id з самого шляху URL)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable Long id) {
        ordersService.deleteOrdersWithID(id);
        return ResponseEntity.ok("Видалено");
    }

    // 4. Пошук (Ловимо параметр phone з URL: ?phone=...)
    @GetMapping("/search")
    public ResponseEntity<List<Orders>> searchOrders(@RequestParam("phone") String number) {
        List<Orders> searchedOrders = ordersService.searchOrderWithPhoneNumber(number);
        return ResponseEntity.ok(searchedOrders);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders updatedOrder) {
        Orders savedOrder = ordersService.updateOrder(id, updatedOrder);
        return ResponseEntity.ok(savedOrder); // Повертаємо статус 200 OK та оновлений об'єкт
    }
    @GetMapping("/stats")
    public ResponseEntity<OrderStatusStatistics> getOrderStatus() {
        OrderStatusStatistics status = ordersService.getStatusStatistics();
        return ResponseEntity.ok(status);
    }
}