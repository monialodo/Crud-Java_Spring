package com.feeltech.projeto.controller;

import com.feeltech.projeto.model.Restaurante;
import com.feeltech.projeto.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurante")
@Tag(name = "Restaurantes", description = "Endpoints para gerenciamento de restaurantes")
public class RestaurantController {

    @Autowired
    private RestauranteService restauranteService;


    @GetMapping
    @Operation(
            summary = "Lista todos os restaurantes",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <List<Restaurante>> findAll() {
        return ResponseEntity.ok(restauranteService.listarTodos());
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Lista todos os restaurantes por ID",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <Optional<Restaurante>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.listarPorId(id));
    }

    @GetMapping(path = "/listar-nome")
    @Operation(
            summary = "Lista todos os restaurantes por nome",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <List<Restaurante>> findByName(@RequestParam String nome) {
        return ResponseEntity.ok(restauranteService.listarPorNome(nome));
    }

    @GetMapping(path = "/listar-culinaria")
    @Operation(
            summary = "Lista todos os restaurantes por tipo de culinária",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <List <Restaurante>> findByFood(@RequestParam String tipoCulinaria) {
        return ResponseEntity.ok(restauranteService.listarPorTipoCulinaria(tipoCulinaria));
    }

    @GetMapping(path = "/listar-cep")
    @Operation(
            summary = "Lista todos os restaurantes por tipo de culinária",
            tags = {"Restaurantes"}
    )
    public ResponseEntity<List<Restaurante>> findByCep(@RequestParam String cep) {
        return ResponseEntity.ok(restauranteService.listarPorCep(cep));
    }

    @PostMapping
    @Operation(
            summary = "Cria restaurantes",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <Restaurante> criar(@RequestBody Restaurante restaurante) {
        restauranteService.salvarRestaurante(restaurante);
        return ResponseEntity.ok(restaurante);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Atualiza restaurantes por ID",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <Restaurante> atualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        restauranteService.atualizarRestaurante(id, restaurante);
        return ResponseEntity.ok(restaurante);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Deleta restaurantes por ID",
            tags = {"Restaurantes"}
    )
    public ResponseEntity <Void> deletarRestaurante(@PathVariable Long id) {
        restauranteService.deletarRestaurante(id);
        return ResponseEntity.noContent().build();
    }

}
