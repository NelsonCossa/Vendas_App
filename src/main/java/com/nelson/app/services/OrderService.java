package com.nelson.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelson.app.entities.enums.OrderStatus;

import com.nelson.app.entities.Order;
import com.nelson.app.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Encomenda não encontrada com ID: " + id));
    }
    public List<Order> findByStatus(OrderStatus status) {
        return repository.findByOrderStatus(status.getCode());
    }
    public Order insert(Order order) {
        return repository.save(order);
    }

    public Order update(Long id, Order order) {
        Order entity = findById(id);
        entity.setMoment(order.getMoment()); // Exemplo: ajusta os campos necessários conforme a tua entidade
        entity.setOrderStatus(order.getOrderStatus());
        // Se tiveres cliente, itens, pagamento, etc., atualiza conforme necessário
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
