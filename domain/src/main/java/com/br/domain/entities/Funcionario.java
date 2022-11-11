package com.br.domain.entities;

import lombok.Data;

@Data
public class Funcionario extends Usuario {

    private CarteiraTrabalho carteiraTrabalho;
    private Pis pis;

    public Funcionario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone,
                       Endereco endereco, String cargo, CarteiraTrabalho carteiraTrabalho, Pis pis) {
        super(nome, sobrenome, cpf, email, telefone, endereco, cargo);
        this.carteiraTrabalho = carteiraTrabalho;
        this.pis = pis;
    }

    public Funcionario(){
        super();
    }

    @Override
    public String getCargo(String cargo) {
        return cargo;
    }

}
