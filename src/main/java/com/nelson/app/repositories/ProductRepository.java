package com.nelson.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelson.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
