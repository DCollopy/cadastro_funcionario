package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.teste.RelatorioDecimoTerceiroTeste;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioDecimoTerceiroTest {

    protected RelatorioDecimoTerceiroTeste relatorioDecimoTerceiroTeste = new RelatorioDecimoTerceiroTeste();

    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "12345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910")
            , new Pis("12345678910"), new Salario(10000.00), 2, LocalDate.of(2020, 1, 1));

    RelatorioDecimoTerceiro relatorioDecimoTerceiro = new RelatorioDecimoTerceiro(funcionario, 0, "Teste");

    @Test
    void calculaDecimoTerceiro() {
        assertNotNull(relatorioDecimoTerceiroTeste.criaPrimeiraParcelaDecimo13(relatorioDecimoTerceiro));
    }

    @Test
    void calculaDecimoTerceiroUnicaParcela() {
        assertNotNull(relatorioDecimoTerceiroTeste.criaSegundaParcelaDecimo13(relatorioDecimoTerceiro));
    }

    @Test
    void calculoDecimoTerceiro2() {
        relatorioDecimoTerceiro.setSalarioBrutoDecimoTerceiro(5000.00);
        assertNotNull(relatorioDecimoTerceiroTeste.criaSegundaParcelaDecimo13(relatorioDecimoTerceiro));
    }

}