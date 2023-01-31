package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;
import br.com.cadastro.dominio.entidade.RelatorioFerias;
import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.dominio.entidade.objetos.*;
import br.com.cadastro.dominio.usecases.excel.GerarExcelSalario;
import br.com.cadastro.dominio.usecases.excel.IGerarExcelSalario;
import br.com.cadastro.dominio.usecases.teste.RelatorioMensalValidaTeste;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GerarExcelSalarioTest {

    protected IGerarExcelSalario gerarExcelSalario = new GerarExcelSalario();

    protected RelatorioMensalValidaTeste relatorioMensalValidaTeste = new RelatorioMensalValidaTeste();
    Funcionario funcionario = new Funcionario("Joao", "Silva"
            , new Cpf("123.456.789-10")
            , new Email("teste@email.com")
            , new Telefone("12", "112345678")
            , new Endereco("Rua teste", "123", "Bairro teste", "Cidade teste", new Cep("13456-780"))
            ,"Gerente", new CarteiraTrabalho("12345678910")
            , new Pis("12345678910"), new Salario(10000.00),2, LocalDate.of(2020, 1, 1));
    private String FILE_NAME = "RelatorioMensal.xlsx";
    private String fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
    RelatorioMensal relatorioMensal = new RelatorioMensal(funcionario, 100.00, "Teste");

    RelatorioDecimoTerceiro relatorioDecimoTerceiro = new RelatorioDecimoTerceiro(funcionario, 0, "Teste");

    RelatorioFerias relatorioFeriasNew = new RelatorioFerias(funcionario,100.00, "Teste");

    @Test
    void gerarExcelSalario(){
        RelatorioMensal relatorioMensalGerado = relatorioMensalValidaTeste.criaRelatorioMensal(relatorioMensal);
        gerarExcelSalario.gerarExcelSalario(relatorioMensalGerado, fileLocation);
    }

    @Test
    void gerarExcelDecimoTerceiro(){
        String FILE_NAME = "RelatorioDecimoTerceiro.xlsx";
        String fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
        gerarExcelSalario.gerarExcelDecimoTerceiro(relatorioDecimoTerceiro, fileLocation);
    }

    @Test
    void gerarExcelFerias(){
        String FILE_NAME = "RelatorioFerias.xlsx";
        String fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
        gerarExcelSalario.gerarExcelFerias(relatorioFeriasNew, fileLocation);
    }
}