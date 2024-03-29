package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.teste.RelatorioMensalValidaTeste;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

public class RelatorioMensalTest {

    protected RelatorioMensalValidaTeste relatorioMensalValidaTeste = new RelatorioMensalValidaTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "112345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910")
            , new Pis("12345678910"), new Salario(10000.00),2, LocalDate.of(2020, 1, 1));

    Set<Funcionario> funcionariosSet = Set.of(funcionario);
    RelatorioMensal relatorioMensal = new RelatorioMensal(funcionariosSet, 100.00, "Teste");

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