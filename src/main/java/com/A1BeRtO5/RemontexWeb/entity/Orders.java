package com.A1BeRtO5.RemontexWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // розібрати інші значення
    private Long id;

    private String phoneNumber;
    private String serialNumber;
    private String client;
    private LocalDate registrationDate;
    private String ordersGroup;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String color;
}
