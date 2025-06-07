package com.nelson.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelson.app.entities.Product;
import com.nelson.app.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    public Product insert(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        Product entity = findById(id);
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());
        // Atualizar outras propriedades conforme a tua entidade
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
