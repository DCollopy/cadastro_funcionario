package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.RelatorioMensal;


import java.time.LocalDate;
import java.util.List;

public interface RelatorioMensalService {

    void criaRelatorioMensal(String cpf);

    RelatorioMensal editaRelatorioMensal(RelatorioMensal relatorioMensal);

    List<RelatorioMensal> listaRelatorioMensalPorFuncionario(String cpf);

    RelatorioMensal buscarRelatorioMensalPorMes(LocalDate mesRelatorio, String cpf);

    void gerarExcelRelatorio(String cpf);
}