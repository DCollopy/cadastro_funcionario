package br.com.cadastro.persistencia.repositorio;

import br.com.cadastro.persistencia.entidade.LoginEntidade;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo {

    LoginEntidade salvar(LoginEntidade loginEntidade);

    LoginEntidade atualizar(LoginEntidade loginEntidade);

    void excluir(LoginEntidade loginEntidade) ;

    LoginEntidade buscarPorCpf(String cpf) ;

    LoginEntidade buscarPorEmail(String email);

    Boolean existePorCpf(String cpf);
}
