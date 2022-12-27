package br.com.cadastro.domain.entities;

import br.com.cadastro.domain.entities.objetos.*;
import lombok.Data;

@Data
public class Funcionario extends Usuario {

    private CarteiraTrabalho carteiraTrabalho;
    private Pis pis;

    private Salario salario_bruto;

    public Funcionario(){
        super();
    }
    public Funcionario(String nome, String sobrenome
            , Cpf cpf, Email email, Telefone telefone
            , Endereco endereco, String cargo
            , CarteiraTrabalho carteiraTrabalho
            , Pis pis, Salario salario_bruto) {
        super(nome, sobrenome, cpf, email, telefone, endereco, cargo);
        this.carteiraTrabalho = carteiraTrabalho;
        this.pis = pis;
        this.salario_bruto = salario_bruto;
    }

    @Override
    public String getCargo(String cargo) {
        return cargo;
    }

}