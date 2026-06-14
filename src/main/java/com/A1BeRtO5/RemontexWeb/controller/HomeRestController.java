package com.A1BeRtO5.RemontexWeb.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
    private final OrdersController ordersController;

    public HomeRestController(OrdersController ordersController) {
        this.ordersController = ordersController;
    }
}
