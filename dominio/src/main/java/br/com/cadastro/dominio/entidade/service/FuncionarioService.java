package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Funcionario;

import java.util.List;

public interface FuncionarioService {

    Funcionario criaFuncionario(Funcionario funcionario, String cnpj);
    List<Funcionario> criaFuncionarios(List<Funcionario> funcionario, String cnpj);

    Funcionario editaFuncionario(Funcionario funcionario);

    List<Funcionario> listaFuncionarios();

    void excluirFuncionario(String cpf);

    Funcionario buscaFuncionario(String cpf);

    boolean existeFuncionario(String cpf);
}
