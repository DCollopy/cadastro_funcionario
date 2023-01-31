package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.RelatorioMensal;

public interface IGerarExcelSalario {
    void gerarExcelSalario(RelatorioMensal relatorioMensal, String caminhaArquivo) throws Exception;
}
