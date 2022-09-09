package com.feeltech.projeto.model;

import javax.persistence.*;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String tipoCulinaria;

    @ManyToOne (cascade = CascadeType.MERGE )
    @JoinColumn(name = "cep")
    private Endereco endereco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoCulinaria() {
        return tipoCulinaria;
    }

    public void setTipoCulinaria(String tipoCulinaria) {
        this.tipoCulinaria = tipoCulinaria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
