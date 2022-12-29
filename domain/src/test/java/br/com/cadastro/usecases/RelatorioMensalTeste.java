package br.com.cadastro.usecases;

import br.com.cadastro.domain.entities.Funcionario;
import br.com.cadastro.domain.entities.RelatorioMensal;
import br.com.cadastro.domain.entities.objetos.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class RelatorioMensalTeste {

    protected RelatorioMensalValidaTeste relatorioMensalValidaTeste = new RelatorioMensalValidaTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "12345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910"), new Pis("12345678910"), new Salario(10000.00),2);

    RelatorioMensal relatorioMensal = new RelatorioMensal(funcionario, 100.00, "Teste");

    @Test
    void criaRelatorioMensal() {
        assertNotNull(relatorioMensalValidaTeste.criaRelatorioMensal(relatorioMensal));
    }

    @Test
    void naoCriaRelatorioMensal() {
        assertThrows(IllegalArgumentException.class, () -> relatorioMensalValidaTeste
                .criaRelatorioMensal( new RelatorioMensal(null, 100.00, "Teste")));
    }

    @Test
    void validaExisteRelatorioMensal() {
        assertTrue(relatorioMensalValidaTeste.validaExiteRelatorioGerado(relatorioMensal));
    }

    @Test
    void editaRelatorioMensal() {
        relatorioMensal.setObservacoes("Teste 2");
        assertEquals("Teste 2",  relatorioMensalValidaTeste.editaRelatorioMensal(relatorioMensal).getObservacoes());
    }
}