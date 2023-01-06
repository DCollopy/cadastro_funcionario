package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Admin;

import java.util.List;

public interface AdminService {
    void criaAdmin(Admin admin);
    Admin editaAdmin(Admin admin);
    Admin buscaAdmin(int id);
    List<Admin> listaAdmin();
    void deletaAdmin(int id);
}