package com.A1BeRtO5.RemontexWeb.service;

import com.A1BeRtO5.RemontexWeb.entity.OrderStatus;
import com.A1BeRtO5.RemontexWeb.entity.OrderStatusStatistics;
import com.A1BeRtO5.RemontexWeb.entity.Orders;
import com.A1BeRtO5.RemontexWeb.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public void saveOrder(Orders order) {
        ordersRepository.save(order);
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Приймаємо чистий ID, вбудований метод deleteById в Spring Data нічого не повертає (void)
    public void deleteOrdersWithID(Long id) {
        ordersRepository.deleteById(id);
    }

    // Приймаємо рядок телефону і повертаємо список знайдених замовлень
    public List<Orders> searchOrderWithPhoneNumber(String phone) {
        // Тут має бути твій метод з репозиторію. Наприклад: findByPhoneNumberContaining
        return ordersRepository.findByPhoneNumberContaining(phone);
    }
    public Orders updateOrder(Long id, Orders updatedOrder) {
        // 1. Шукаємо замовлення в базі. Якщо немає — викидаємо помилку
        Orders changeOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Замовлення з ID " + id + " не знайдено"));


        changeOrder.setPhoneNumber(updatedOrder.getPhoneNumber());
        changeOrder.setSerialNumber(updatedOrder.getSerialNumber());
        changeOrder.setClient(updatedOrder.getClient());
        changeOrder.setRegistrationDate(updatedOrder.getRegistrationDate());
        changeOrder.setOrdersGroup(updatedOrder.getOrdersGroup());
        changeOrder.setPrice(updatedOrder.getPrice());
        changeOrder.setStatus(updatedOrder.getStatus());
        changeOrder.setColor(updatedOrder.getColor());

        return ordersRepository.save(changeOrder);
    }
    public OrderStatusStatistics getStatusStatistics() {
        long inRepair = ordersRepository.countByStatus(OrderStatus.IN_REPAIR);
        long urgent = ordersRepository.countByStatus(OrderStatus.URGENT);
        long ready = ordersRepository.countByStatus(OrderStatus.READY);
        long issued = ordersRepository.countByStatus(OrderStatus.ISSUED);
        long total = ordersRepository.count();
        return new OrderStatusStatistics(inRepair, urgent, ready, issued, total);
    }
}