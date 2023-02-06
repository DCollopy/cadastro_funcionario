package br.com.cadastro.persistencia.service.funcionario;

import br.com.cadastro.dominio.entidade.Funcionario;

import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.repositorio.FuncionarioRepositorio;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FuncionarioIml implements FuncionarioService {

    private final FuncionarioRepositorio funcionarioRepositorio;

    private final FuncionarioMapper funcionarioMapper;

    private final CpfMapper cpfMapper;

    private FuncionarioValidaAbs funcionarioValidaAbs = new FuncionarioValidaAbs();

    public FuncionarioIml(FuncionarioRepositorio funcionarioRepositorio, FuncionarioMapper funcionarioMapper, CpfMapper cpfMapper) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.funcionarioMapper = funcionarioMapper;
        this.cpfMapper = cpfMapper;
    }

    @Override
    public Funcionario criaFuncionario(Funcionario funcionario) {
        Funcionario funcioValida = funcionarioValidaAbs.criaFuncionario(funcionario);
        FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcioValida);
        return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.salvar(funcionarioEntidade));
    }

    @Override
    public List<Funcionario> criaFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            Funcionario funcioValida = funcionarioValidaAbs.criaFuncionario(funcionario);
            FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcioValida);
            funcionarioRepositorio.salvar(funcionarioEntidade);
        }
        return funcionarios;
    }

    @Override
    public Funcionario editaFuncionario(Funcionario funcionario) {
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(funcionario.getCpf());
        FuncionarioEntidade existe = funcionarioRepositorio.encontrePorCpf(cpfEntidade);
        if (existe != null) {
            return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.editar(existe));
        }

        return funcionarioMapper.converteEntidadeToFuncionario(existe);
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.listaFuncionarios());
    }

    @Override
    public void excluirFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        if(funcionarioRepositorio.existeCpf(cpfEntidade)){
            funcionarioRepositorio.excluir(cpfEntidade);
        }
        Logger.getLogger("FUNCIONARIO WEB").info("Funcionario não encontrado");
        throw new IllegalArgumentException("Funcionario não encontrado");
    }

    @Override
    public Funcionario buscaFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.encontrePorCpf(cpfEntidade));
    }

    @Override
    public boolean existeFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        return funcionarioRepositorio.existeCpf(cpfEntidade);
    }
}
