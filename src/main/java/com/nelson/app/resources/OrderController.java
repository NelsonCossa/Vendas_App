package com.nelson.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelson.app.entities.Order;
import com.nelson.app.entities.enums.OrderStatus;
import com.nelson.app.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Order", description = "API para operações relacionadas a encomendas")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    @Operation(summary = "Listar todas as encomendas",
               description = "Retorna uma lista com todas as encomendas registadas no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de encomendas retornada com sucesso")
    })
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar encomenda por ID",
               description = "Retorna uma encomenda específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encomenda encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Encomenda não encontrada")
    })
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    
    @GetMapping("/status")
    @Operation(summary = "Listar encomendas por estado", description = "Retorna todas as encomendas com o estado especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de encomendas retornada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Estado inválido")
    })
    public ResponseEntity<List<Order>> findByStatus(@RequestParam("code") Integer code) {
        try {
            OrderStatus status = OrderStatus.valueOf(code);
            List<Order> list = service.findByStatus(status);
            return ResponseEntity.ok().body(list);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping
    @Operation(summary = "Inserir nova encomenda",
               description = "Cria uma nova encomenda no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Encomenda criada com sucesso")
    })
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        Order saved = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar encomenda",
               description = "Atualiza uma encomenda existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Encomenda atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Encomenda não encontrada")
    })
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        Order updated = service.update(id, order);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover encomenda",
               description = "Remove uma encomenda pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Encomenda removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Encomenda não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
