package com.feeltech.projeto.repository;

import com.feeltech.projeto.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long > {

    @Query("SELECT e FROM Restaurante e WHERE e.nome = :nome")
    List<Restaurante> findByName (String nome);

    @Query("SELECT e FROM Restaurante e WHERE e.tipoCulinaria = :tipoCulinaria")
    List <Restaurante> findByFood (String tipoCulinaria);

    @Query("SELECT e FROM Restaurante e WHERE e.endereco.cep = :cep")
    List<Restaurante> findAllByCep (String cep);







}
