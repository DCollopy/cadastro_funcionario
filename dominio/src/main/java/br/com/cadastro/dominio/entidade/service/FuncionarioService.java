package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Funcionario;

import java.util.List;

public interface FuncionarioService {

    Funcionario criaFuncionario(Funcionario funcionario);

    Funcionario editaFuncionario(String cpf);

    List<Funcionario> listaFuncionarios();

    void deletaFuncionario(String cpf);

    Funcionario buscaFuncionario(String cpf);

    boolean existeFuncionario(String cpf);
}
