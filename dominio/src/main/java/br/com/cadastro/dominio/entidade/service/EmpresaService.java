package br.com.cadastro.dominio.entidade.service;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.dominio.entidade.Funcionario;

import java.util.List;

public interface EmpresaService {

    void salvar(Empresa empresa);

    Empresa encontrePorCnpj(String cnpj);

    List<Funcionario> listarFuncionarios(String cnpj);

    Boolean existePorCnpj(String cnpj);

    void salvarLote(List<Empresa> empresas);

    List<Empresa> listarEmpresas();

    Empresa editar(Empresa empresa);

    void excluir(String cnpj);
}
