package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Login;

public interface LoginService {

    void salva(Login login);

    Login buscaPorCpf(String cpf);

    Login buscaPorEmail(String email);

    void excluir(String cpf);

    Login fazerLogin(String email, String senha);
}
