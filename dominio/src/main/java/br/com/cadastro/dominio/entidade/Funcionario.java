package br.com.cadastro.dominio.entidade;

import br.com.cadastro.dominio.entidade.objetos.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.logging.Logger;

@Data
@SuperBuilder
public class Funcionario extends Usuario {

    private CarteiraTrabalho carteiraTrabalho;
    private Pis pis;

    private Salario salario_bruto;

    private int dependentes = 0;

    private LocalDate data_admissao;

    private Empresa empresa;

    public Funcionario(){
        super();
    }
    public Funcionario(String nome, String sobrenome
            , Cpf cpf, Email email, Telefone telefone
            , Endereco endereco, String cargo
            , CarteiraTrabalho carteiraTrabalho
            , Pis pis, Salario salario_bruto
            , int dependentes, LocalDate data_admissao) {
        super(nome, sobrenome, cpf, email, telefone, endereco, cargo);

        if(carteiraTrabalho == null || pis == null || salario_bruto == null) {
            Logger.getLogger("FUNCIONARIO").info("Dados invalidos");
            throw new IllegalArgumentException("Dados do funcionario invalidos");
        }
        if(data_admissao == null) {
            Logger.getLogger("FUNCIONARIO").info("Data de admissao invalida");
            throw new IllegalArgumentException("Data de admissao invalida");
        }

        this.carteiraTrabalho = carteiraTrabalho;
        this.pis = pis;
        this.salario_bruto = salario_bruto;
        this.dependentes = dependentes;
        this.data_admissao = data_admissao;
    }

    @Override
    public String getCargo(String cargo) {
        return cargo;
    }

}