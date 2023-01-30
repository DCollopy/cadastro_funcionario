package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.teste.FuncionarioValidaTeste;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    protected FuncionarioValidaTeste funcionarioValidaTeste = new FuncionarioValidaTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "112345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910")
            , new Pis("12345678910"), new Salario(10000.00), 2, LocalDate.of(2020, 1, 1));

    @Test
    void criaFuncionario() {
        assertNotNull(funcionarioValidaTeste.criaFuncionario(funcionario));
    }

    @Test
    void naoCriaFuncionario() {
        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(null));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario(null, "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2, LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", ""
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", null
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , null
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , null
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", null
                , new Pis("12345678910"), new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                ,null, new Salario(10000.00),2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"), null,2,LocalDate.of(2020, 1, 1))));

        assertThrows(IllegalArgumentException.class, () -> funcionarioValidaTeste.criaFuncionario(new Funcionario("Joao", "Silva"
                , new Cpf("123.456.789-10")
                , new Email("teste@email.com")
                , new Telefone("12", "112345678")
                , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("12345-678"))
                ,"Gerente", new CarteiraTrabalho("12345678910")
                , new Pis("12345678910"),  new Salario(10000.00),2,null)));
    }
}