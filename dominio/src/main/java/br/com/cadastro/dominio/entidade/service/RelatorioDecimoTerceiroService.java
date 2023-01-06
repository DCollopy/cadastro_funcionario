package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.RelatorioDecimoTerceiro;

import java.util.List;

public interface RelatorioDecimoTerceiroService {

    void criaRelatorioDecimoTerceiro(RelatorioDecimoTerceiro relatorioDecimoTerceiro);

    RelatorioDecimoTerceiro editaRelatorioDecimoTerceiro(RelatorioDecimoTerceiro relatorioDecimoTerceiro);

    RelatorioDecimoTerceiro buscaRelatorioDecimoTerceiroPorId(Long id);

    List<RelatorioDecimoTerceiro> listaRelatorioDecimoTerceiro();

    void deletaRelatorioDecimoTerceiro(int id);
}
