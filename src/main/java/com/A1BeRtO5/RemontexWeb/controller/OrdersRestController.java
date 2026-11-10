package com.A1BeRtO5.RemontexWeb.controller;

import com.A1BeRtO5.RemontexWeb.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor// конструктор лише для нотналів і файнелів
@CrossOrigin(origins = "*") // Каже браузеру: "Дозволь сайтам з інших портів (наприклад, з Live Server) брати дані з цього бекенду"
//@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrdersService ordersService;  // ioc



}