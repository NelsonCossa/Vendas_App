package com.nelson.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelson.app.entities.Category;
import com.nelson.app.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }

    public Category insert(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category category) {
        Category entity = findById(id);
        entity.setName(category.getName()); // Supondo que há um atributo "name"
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
