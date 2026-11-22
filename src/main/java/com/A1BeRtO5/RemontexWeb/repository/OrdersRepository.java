package com.A1BeRtO5.RemontexWeb.repository;

import com.A1BeRtO5.RemontexWeb.entity.OrderStatus;
import com.A1BeRtO5.RemontexWeb.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
     List<Orders> findByPhoneNumber(String phoneNumber);
     //отримання кількості замовлень
    long countByStatus(OrderStatus status);
    long countByIssueDateBefore(LocalDate localDate);

}
