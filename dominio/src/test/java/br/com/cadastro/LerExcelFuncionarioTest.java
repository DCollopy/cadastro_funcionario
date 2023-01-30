package br.com.cadastro;

import br.com.cadastro.dominio.usecases.excel.LerExcelFuncionario;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LerExcelFuncionarioTest {

    protected LerExcelFuncionario lerExcelFuncionario = new LerExcelFuncionario();

    private static String FILE_NAME = "funcionarios.xlsx";
    private String fileLocation;

    @Test
    void leituraExcel() throws IOException {
        fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
        assertNotNull(lerExcelFuncionario.leituraExcel(fileLocation));
    }

    @Test
    void leituraExcelException() throws IOException {
        fileLocation = new File("src/test/java/br/com/cadastro/teste/" + "teste.csv").getAbsolutePath();
        assertThrows(IOException.class, () -> lerExcelFuncionario.leituraExcel(fileLocation));
    }
}