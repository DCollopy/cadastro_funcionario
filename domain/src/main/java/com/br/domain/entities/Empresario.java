package com.br.domain.entities;

import lombok.Data;

@Data
public class Empresario extends Usuario {

    private Cnpj cnpj;

    private final String cargo = "Empresário";

    public Empresario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco, Cnpj cnpj) {
        super(nome, sobrenome, cpf, email, telefone, endereco);
        this.cnpj = cnpj;
    }



    @Override
    public String getCargo(String cargo) {
        return this.cargo;
    }
}
