package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioFerias;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.teste.RelatorioFeriasValidaTeste;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class RelatorioFeriasTest {

    protected RelatorioFeriasValidaTeste relatorioFerias = new RelatorioFeriasValidaTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "12345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00), 2);

    RelatorioFerias relatorioFeriasNew = new RelatorioFerias(funcionario,100.00, "Teste");

    @Test
    void criarRelatorioFerias() {
        assertNotNull(relatorioFerias.criaRelatorioFerias(relatorioFeriasNew));
    }
}