package com.nelson.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelson.app.entities.User;
import com.nelson.app.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(value = "/users")
@Tag(name = "User", description = "API para operações relacionadas a utilizadores")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "Listar todos os utilizadores",
               description = "Retorna uma lista com todos os utilizadores registados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de utilizadores retornada com sucesso")
    })
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar utilizador por ID",
               description = "Retorna um utilizador específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Utilizador encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Utilizador não encontrado")
    })
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Operation(summary = "Criar novo utilizador",
               description = "Insere um novo utilizador no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Utilizador criado com sucesso")
    })
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Eliminar utilizador por ID",
               description = "Remove um utilizador do sistema pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Utilizador eliminado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Utilizador não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar utilizador",
               description = "Atualiza os dados de um utilizador existente pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Utilizador atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Utilizador não encontrado")
    })
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
