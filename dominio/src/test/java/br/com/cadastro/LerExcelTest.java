package br.com.cadastro;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.usecases.excel.LerExcel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LerExcelTest {

    protected LerExcel lerExcel = new LerExcel();

    private static String FILE_NAME = "funcionarios.xlsx";
    private String fileLocation;

    @Test
    void leituraExcel() throws IOException {
        fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
        List<Funcionario> funcionarios = lerExcel.leituraExcel(fileLocation);
        assertNotNull(funcionarios);
    }

    @Test
    void leituraExcelException() throws IOException {
        fileLocation = new File("src/test/java/br/com/cadastro/teste/" + "teste.csv").getAbsolutePath();
        assertThrows(IOException.class, () -> lerExcel.leituraExcel(fileLocation));
    }
}