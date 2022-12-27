package br.com.cadastro.domain.entities.service;

import br.com.cadastro.domain.entities.Funcionario;

public interface FuncionarioService {

    Funcionario criaFuncionario(Funcionario funcionario);

    Funcionario editaFuncionario(String cpf);

    void deletaFuncionario(String cpf);

    Funcionario buscaFuncionario(String cpf);

    boolean existeFuncionario(String cpf);
}
