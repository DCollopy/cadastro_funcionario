package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.RelatorioFerias;

import java.util.List;

public interface RelatorioFeriasService {

    void criaRelatorioFerias(RelatorioFerias relatorioFerias);

    RelatorioFerias editaRelatorioFerias(RelatorioFerias relatorioFerias);

    List<RelatorioFerias> listaRelatorioFerias();

    RelatorioFerias buscaRelatorioFeriasPorId(Long id);

    void deletaRelatorioFerias(int id);
}