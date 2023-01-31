package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class GerarExcelSalario implements IGerarExcelSalario {

    @Override
    public void gerarExcelSalario(RelatorioMensal relatorioMensal, String caminhoArquivo)  {
        AtomicInteger lastRow = new AtomicInteger();
        AtomicInteger lastCell = new AtomicInteger();
        Object[][] relatorio = {
                {"TITULO", relatorioMensal.getTitulo()}
                ,{"DATA RELATORIO", relatorioMensal.getMesRelatorio().toString()}
                ,{"NOME", relatorioMensal.getFuncionario().getNome()}
                ,{"SOBRENOME", relatorioMensal.getFuncionario().getSobrenome()}
                ,{"DEPENDENTES", relatorioMensal.getFuncionario().getDependentes()}
                ,{ "IMPOSTO DE RENDA", relatorioMensal.getDescontoIrrf()}
                ,{"INSS", relatorioMensal.getDescontoInss()}
                ,{"OUTROS DESCONTOS", relatorioMensal.getOutrosDescontos()}
                ,{"TOTAL DE DESCONTOS", relatorioMensal.getDescontoTotal()}
                ,{"SALARIO BRUTO", relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto()}
                ,{"SALARIO LIQUIDO", relatorioMensal.getSalarioLiquido()}
        };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("RelatorioMensal");

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : relatorio) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(caminhoArquivo);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger("GerarExcelSalario").info(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getLogger("GerarExcelSalario").info(e.getMessage());
            e.printStackTrace();
        }
        Logger.getLogger("GerarExcelSalario").info("Arquivo gerado com sucesso");
    }
}
