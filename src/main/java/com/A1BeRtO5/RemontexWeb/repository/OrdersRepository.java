package com.A1BeRtO5.RemontexWeb.repository;

import com.A1BeRtO5.RemontexWeb.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
