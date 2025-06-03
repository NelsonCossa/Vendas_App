package com.nelson.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelson.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
