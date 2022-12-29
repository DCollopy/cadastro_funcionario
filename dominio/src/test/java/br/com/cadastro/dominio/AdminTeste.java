package br.com.cadastro.dominio;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.AdminValidaTeste;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTeste {

    protected AdminValidaTeste adminValidaTeste = new AdminValidaTeste();

    Admin admin = new Admin("Katia","Santos"
            , new Cpf("123.456.789-10"), new Email("katias@email.com")
            , new Telefone("22","12345678")
            , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
            , new Cnpj("12.345.678/9012-34"));

    @Test
    void criaAdmin(){
        assertNotNull(adminValidaTeste.criaAdmin(admin));
    }

    @Test
    void naoCriaAdmin(){
        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(null));

        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin(null,"Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("","Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia",null
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia",""
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , null, new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , new Cpf("123.456.789-10"), null
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , null
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , null
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , new Cnpj("12.345.678/9012-34"))));


        assertThrows(IllegalArgumentException.class, () -> adminValidaTeste.criaAdmin(new Admin("Katia","Santos"
                , new Cpf("123.456.789-10"), new Email("katias@email.com")
                , new Telefone("22","12345678")
                , new Endereco("Rua 1","123","Centro","Rio de Janeiro",new Cep("12345-678"))
                , null)));
    }
}