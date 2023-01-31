package br.com.cadastro.dominio.usecases.excel;

import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;
import br.com.cadastro.dominio.entidade.RelatorioFerias;
import br.com.cadastro.dominio.entidade.RelatorioMensal;

public interface IGerarExcelSalario {
    void gerarExcelSalario(RelatorioMensal relatorioMensal, String caminhaArquivo);
    void gerarExcelFerias(RelatorioFerias relatorioFerias, String caminhoArquivo);

    void gerarExcelDecimoTerceiro(RelatorioDecimoTerceiro relatorioDecimoTerceiro, String caminhoArquivo);
}
