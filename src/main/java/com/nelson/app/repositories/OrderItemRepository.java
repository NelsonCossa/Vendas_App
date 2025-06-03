package com.nelson.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelson.app.entities.OrderItem;
import com.nelson.app.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
