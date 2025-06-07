package com.nelson.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelson.app.entities.Category;
import com.nelson.app.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Category", description = "API para operações relacionadas a categorias")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista com todas as categorias cadastradas no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Operation(summary = "Inserir nova categoria", description = "Cria uma nova categoria no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    })
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        Category saved = service.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updated = service.update(id, category);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria", description = "Remove uma categoria pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
