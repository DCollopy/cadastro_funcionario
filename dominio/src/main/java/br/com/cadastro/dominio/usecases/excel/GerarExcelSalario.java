package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;
import br.com.cadastro.dominio.entidade.RelatorioFerias;
import br.com.cadastro.dominio.entidade.RelatorioMensal;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.logging.Logger;

public class GerarExcelSalario implements IGerarExcelSalario {

    @Override
    public void gerarExcelSalario(RelatorioMensal relatorioMensal, String caminhoArquivo)  {
        Funcionario funcionario = relatorioMensal.getFuncionario().stream().iterator().next();
        Object[][] relatorio = {
                {"TITULO", relatorioMensal.getTitulo()}
                ,{"DATA RELATORIO", relatorioMensal.getMesRelatorio().toString()}
                ,{"NOME", funcionario.getNome()}
                ,{"SOBRENOME", funcionario.getSobrenome()}
                ,{"DEPENDENTES", funcionario.getDependentes()}
                ,{ "IMPOSTO DE RENDA", relatorioMensal.getDescontoIrrf()}
                ,{"INSS", relatorioMensal.getDescontoInss()}
                ,{"OUTROS DESCONTOS", relatorioMensal.getOutrosDescontos()}
                ,{"TOTAL DE DESCONTOS", relatorioMensal.getDescontoTotal()}
                ,{"SALARIO BRUTO", funcionario.getSalario_bruto().getSalario_bruto()}
                ,{"SALARIO LIQUIDO", relatorioMensal.getSalarioLiquido()}
        };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("RelatorioMensal");

        escreverPlanilha(caminhoArquivo, relatorio, workbook, sheet);
    }

    public void gerarExcelFerias(RelatorioFerias relatorioFerias, String caminhoArquivo){
        Object[][] relatorio = {
                {"TITULO", relatorioFerias.getTitulo()}
                ,{"DATA INICIO FERIAS", relatorioFerias.getDataInicioFerias()}
                ,{"NOME", relatorioFerias.getFuncionario().getNome()}
                ,{"SOBRENOME", relatorioFerias.getFuncionario().getSobrenome()}
                ,{"DEPENDENTES", relatorioFerias.getFuncionario().getDependentes()}
                ,{"IMPOSTO DE RENDA", relatorioFerias.getDescontoIrrf()}
                ,{"INSS", relatorioFerias.getDescontoInss()}
                ,{"OUTROS DESCONTOS", relatorioFerias.getOutrosDescontos()}
                ,{"TOTAL DE DESCONTOS", relatorioFerias.getDescontoTotal()}
                ,{"SALARIO BRUTO", relatorioFerias.getFuncionario().getSalario_bruto().getSalario_bruto()}
                ,{"SALARIO LIQUIDO", relatorioFerias.getSalarioLiquido()}
        };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("RelatorioFerias");

        escreverPlanilha(caminhoArquivo, relatorio, workbook, sheet);
    }

    public void gerarExcelDecimoTerceiro(RelatorioDecimoTerceiro relatorioDecimoTerceiro, String caminhoArquivo){
        Object[][] relatorio = {
                {"TITULO", relatorioDecimoTerceiro.getTitulo()}
                ,{"NOME", relatorioDecimoTerceiro.getFuncionario().getNome()}
                ,{"SOBRENOME", relatorioDecimoTerceiro.getFuncionario().getSobrenome()}
                ,{"DEPENDENTES", relatorioDecimoTerceiro.getFuncionario().getDependentes()}
                ,{"IMPOSTO DE RENDA", relatorioDecimoTerceiro.getDescontoIrrf()}
                ,{"INSS", relatorioDecimoTerceiro.getDescontoInss()}
                ,{"OUTROS DESCONTOS", relatorioDecimoTerceiro.getOutrosDescontos()}
                ,{"TOTAL DE DESCONTOS", relatorioDecimoTerceiro.getDescontoTotal()}
                ,{"SALARIO BRUTO", relatorioDecimoTerceiro.getFuncionario().getSalario_bruto().getSalario_bruto()}
                ,{"SALARIO LIQUIDO", relatorioDecimoTerceiro.getSalarioLiquido()}
        };

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("RelatorioDecimoTerceiro");

        escreverPlanilha(caminhoArquivo, relatorio, workbook, sheet);
    }

    private void escreverPlanilha(String caminhoArquivo, Object[][] relatorio, HSSFWorkbook workbook, HSSFSheet sheet) {
        int rowNum = 0;

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
