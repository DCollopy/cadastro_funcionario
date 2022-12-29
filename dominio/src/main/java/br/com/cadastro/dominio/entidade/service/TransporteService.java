package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Transporte;

public interface TransporteService {

    void criarTransporte( Transporte transporte );

    void editaTransporte(Transporte transporte);

    void deletaTransporte(int id);

    Transporte buscaTransporte(int id);
}