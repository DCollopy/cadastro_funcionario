package com.br.domain.entities;

import lombok.Data;

@Data
public abstract class Usuario {
    private Cpf cpf;
    private String nome;

    private String sobrenome;

    private Email email;

    private Telefone telefone;

    private Endereco endereco;

    private String cargo;

    public Usuario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco, String cargo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cargo = cargo;
    }

    public Usuario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Usuario() {}

    public abstract String getCargo(String cargo);

}
