package com.nelson.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelson.app.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
