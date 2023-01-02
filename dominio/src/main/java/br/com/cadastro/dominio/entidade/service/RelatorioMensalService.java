package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.RelatorioMensal;

import java.util.List;

public interface RelatorioMensalService {

    void criaRelatorioMensal(RelatorioMensal relatorioMensal);

    RelatorioMensal editaRelatorioMensal(RelatorioMensal relatorioMensal);

    List<RelatorioMensal> listaRelatorioMensal();

    RelatorioMensal busqueRelatorioMensalPorId(int id);

    void deletaRelatorioMensal(int id);
}
