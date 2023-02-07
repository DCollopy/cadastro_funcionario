package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.objetos.Cpf;

import java.util.List;

public interface AdminService {
    void criaAdmin(Admin admin);
    void criaAdminLote(List<Admin> admins);
    Admin editaAdmin(Admin admin);
    Admin buscaAdmin(String cpf);
    List<Admin> listaAdmin();
    void excluirAdmin(String cpf);
}