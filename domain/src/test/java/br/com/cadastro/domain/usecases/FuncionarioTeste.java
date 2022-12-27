package br.com.cadastro.domain.usecases;

import br.com.cadastro.domain.entities.Funcionario;
import br.com.cadastro.domain.entities.objetos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTeste {

    protected FuncionarioValidaTeste funcionarioValidaTeste = new FuncionarioValidaTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("12345678910")
            , new Email("teste@email.com")
            , new Telefone("12", "12345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00));

    @Test
    void criaFuncionario() {
        assertNotNull(funcionarioValidaTeste.criaFuncionario(funcionario));
    }

    @Test
    void naoCriaFuncionario() {
        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(null));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario(null, "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", ""
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", null
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , null
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , null
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , null
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , null
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", null, new Pis("12345678910"), new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"),null, new Salario(10000.00))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("12345678910")
                , new Email("teste@email.com")
                , new Telefone("12", "123456789")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345678"))
                ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), null)));
    }

}