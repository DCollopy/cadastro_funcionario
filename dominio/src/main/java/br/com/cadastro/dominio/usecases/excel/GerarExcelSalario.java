package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class GerarExcelSalario implements IGerarExcelSalario {

    @Override
    public void gerarExcelSalario(RelatorioMensal relatorioMensal, String caminhoArquivo) throws IOException {
        int lastRow = 0;
        AtomicInteger lastCell = new AtomicInteger();
        Workbook workbook = new HSSFWorkbook(new FileInputStream(caminhoArquivo));

        Sheet sheet = workbook.getSheetAt(0);
        lastRow = sheet.getLastRowNum();

        sheet.forEach((row) -> {
            lastCell.set(row.getLastCellNum());

            row.forEach((cell) -> {
                //System.out.println(cell.getStringCellValue());
            });
        });

        Cell cell = sheet.createRow(lastRow + 2)
                .createCell(lastCell.getAndIncrement());
        cell.setCellType(CellType.STRING);
        cell.setCellValue(relatorioMensal.getTitulo());
        cell.setCellValue(relatorioMensal.getMesRelatorio());
        cell.setCellValue(relatorioMensal.getFuncionario().getNome());
        cell.setCellValue(relatorioMensal.getFuncionario().getSobrenome());
        cell.setCellValue(relatorioMensal.getDescontoIrrf());
        cell.setCellValue(relatorioMensal.getDescontoInss());
        cell.setCellValue(relatorioMensal.getOutrosDescontos());
        cell.setCellValue(relatorioMensal.getDescontoTotal());
        cell.setCellValue(relatorioMensal.getFuncionario().getSalario_bruto().getSalario_bruto());
        cell.setCellValue(relatorioMensal.getSalarioLiquido());

        workbook.write(new FileOutputStream(caminhoArquivo));
        workbook.close();
    }
}
