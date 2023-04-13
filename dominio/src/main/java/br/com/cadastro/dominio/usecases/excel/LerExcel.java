package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.objetos.*;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class LerExcel implements ILerExcel {

     @Override
     public  List<Funcionario> leituraExcel(String file) throws IOException {
          List<Funcionario> listaFuncionario = new ArrayList<>();

          if(file != null && file.contains(".xlsx")) {

                    FileInputStream fileInputStream = new FileInputStream(file);
                    Workbook workbook = new XSSFWorkbook(fileInputStream);
                    // Aba da planilha
                    Sheet sheet = workbook.getSheetAt(0);

                    List<Row> rowIterator = (List<Row>) toList(sheet.iterator());
                    // Remove o cabeçalho
                    rowIterator.remove(0);

                    rowIterator.forEach( row -> {
                         List<Cell> cells = (List<Cell>) toList(row.cellIterator());
                         if(getStringCellValue(row,0) != null) {
                              Funcionario funcionario = Funcionario.builder()
                                      .nome(cells.get(0).getStringCellValue())
                                      .sobrenome(cells.get(1).getStringCellValue())
                                      .cpf(new Cpf(cells.get(2).getStringCellValue()))
                                      .email(new Email(cells.get(3).getStringCellValue()))
                                      .telefone(new Telefone(cells.get(4).getStringCellValue(), cells.get(5).getStringCellValue()))
                                      .endereco(new Endereco(cells.get(6).getStringCellValue()
                                              , cells.get(7).getStringCellValue()
                                              , cells.get(8).getStringCellValue()
                                              , cells.get(9).getStringCellValue()
                                              ,new Cep(cells.get(10).getStringCellValue())))
                                      .cargo(cells.get(11).getStringCellValue())
                                      .carteiraTrabalho(new CarteiraTrabalho(cells.get(12).getStringCellValue()))
                                      .pis(new Pis(cells.get(13).getStringCellValue()))
                                      .salario_bruto(new Salario(cells.get(14).getNumericCellValue()))
                                      .dependentes((int) cells.get(15).getNumericCellValue())
                                      .data_admissao(cells.get(16).getDateCellValue().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate())
                                      .build();
                              listaFuncionario.add(funcionario);
                         }
                    });
          }
          Logger.getLogger("Arquivo Funcionario").info("Arquivo inválido");
          throw new IOException("Arquivo inválido");
     }

     @Override
     public  List<Admin> leituraExcelAdmin(String file) throws IOException {
          List<Admin> listaAdmin = new ArrayList<>();

          if(file != null && file.contains(".xlsx")) {

               FileInputStream fileInputStream = new FileInputStream(file);
               Workbook workbook = new XSSFWorkbook(fileInputStream);
               // Aba da planilha
               Sheet sheet = workbook.getSheetAt(0);

               List<Row> rowIterator = (List<Row>) toList(sheet.iterator());
               // Remove o cabeçalho
               rowIterator.remove(0);

               rowIterator.forEach( row -> {
                    List<Cell> cells = (List<Cell>) toList(row.cellIterator());
                    if(getStringCellValue(row,0) != null) {
                         Admin admin = Admin.builder()
                                 .nome(cells.get(0).getStringCellValue())
                                 .sobrenome(cells.get(1).getStringCellValue())
                                 .cpf(new Cpf(cells.get(2).getStringCellValue()))
                                 .email(new Email(cells.get(3).getStringCellValue()))
                                 .telefone(new Telefone(cells.get(4).getStringCellValue(), cells.get(5).getStringCellValue()))
                                 .endereco(new Endereco(cells.get(6).getStringCellValue()
                                         , cells.get(7).getStringCellValue()
                                         , cells.get(8).getStringCellValue()
                                         , cells.get(9).getStringCellValue()
                                         ,new Cep(cells.get(10).getStringCellValue())))
                                 .cnpj(new Cnpj(cells.get(11).getStringCellValue()))
                                 .build();
                         listaAdmin.add(admin);
                    }
               });
               workbook.close();
               fileInputStream.close();
               return listaAdmin;
          }
          Logger.getLogger("Arquivo Admin").info("Arquivo inválido");
          throw new IOException("Arquivo inválido");
     }

     public  List<Empresa> leituraExcelEmpresa(String file) throws IOException {
          List<Empresa> listaEmpresa = new ArrayList<>();

          if(file != null && file.contains(".xlsx")) {

               FileInputStream fileInputStream = new FileInputStream(file);
               Workbook workbook = new XSSFWorkbook(fileInputStream);
               // Aba da planilha
               Sheet sheet = workbook.getSheetAt(0);

               List<Row> rowIterator = (List<Row>) toList(sheet.iterator());
               // Remove o cabeçalho
               rowIterator.remove(0);

               rowIterator.forEach( row -> {
                    List<Cell> cells = (List<Cell>) toList(row.cellIterator());
                    if(getStringCellValue(row,0) != null) {
                         Empresa empresa = Empresa.builder()
                                 .razaosocial(cells.get(0).getStringCellValue())
                                 .nomefantasia(cells.get(1).getStringCellValue())
                                 .cnpj(new Cnpj(cells.get(2).getStringCellValue()))
                                 .build();
                         listaEmpresa.add(empresa);
                    }
               });
               workbook.close();
               fileInputStream.close();
               return listaEmpresa;
          }
          Logger.getLogger("Arquivo Empresa").info("Arquivo inválido");
          throw new IOException("Arquivo inválido");
     }

     public List<?> toList(Iterator<?> iterator){
          return IteratorUtils.toList(iterator);
     }

     public void imprimeLista(List<?> lista){
          lista.forEach(System.out::println);
     }

     public static String getStringCellValue (Row row, int cellNum) {
          return row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null ? null : row.getCell(cellNum).getStringCellValue();
     }
}
