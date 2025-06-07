package com.nelson.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelson.app.entities.Product;
import com.nelson.app.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Product", description = "API para operações relacionadas a produtos")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos",
               description = "Retorna uma lista com todos os produtos disponíveis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    })
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar produto por ID",
               description = "Retorna os detalhes de um produto específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Operation(summary = "Inserir novo produto",
               description = "Adiciona um novo produto ao sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    })
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        Product saved = service.insert(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar produto",
               description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.update(id, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remover produto",
               description = "Remove um produto do sistema pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
