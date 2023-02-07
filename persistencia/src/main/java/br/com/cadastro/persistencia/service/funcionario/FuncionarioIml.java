package br.com.cadastro.persistencia.service.funcionario;

import br.com.cadastro.dominio.entidade.Funcionario;

import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.repositorio.FuncionarioRepositorio;
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
        if(funcionarioRepositorio.existeCpf(funcionarioEntidade.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }
        return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.salvar(funcionarioEntidade));
    }

    @Override
    public List<Funcionario> criaFuncionarios(List<Funcionario> funcionarios) {
        try{
            for (Funcionario funcionario : funcionarios) {
                Funcionario funcioValida = funcionarioValidaAbs.criaFuncionario(funcionario);
                FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcioValida);
                if(!funcionarioRepositorio.existeCpf(funcionarioEntidade.getCpf())){
                    throw new RuntimeException("CPF já cadastrado");
                }
                funcionarioRepositorio.salvar(funcionarioEntidade);
            }
            return funcionarios;
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info("Erro ao criar funcionarios");
            throw new IllegalArgumentException("Erro ao criar funcionarios");
        }
    }

    @Override
    public Funcionario editaFuncionario(Funcionario funcionario) {
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(funcionario.getCpf());
        FuncionarioEntidade existe = funcionarioRepositorio.encontrePorCpf(cpfEntidade);
        try{
            if(existe != null){
                FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcionario);
                return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.salvar(funcionarioEntidade));
            } else {
                Logger.getLogger("FUNCIONARIO").info("Funcionario nao existe");
                throw new IllegalArgumentException("Funcionario nao existe");
            }
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info("Erro ao editar funcionario");
            throw new IllegalArgumentException("Erro ao editar funcionario");
        }
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.listaFuncionarios());
    }

    @Override
    public void excluirFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        try {
            if(funcionarioRepositorio.existeCpf(cpfEntidade)){
                funcionarioRepositorio.excluir(cpfEntidade);
            } else {
                Logger.getLogger("FUNCIONARIO").info("Funcionario nao existe");
                throw new IllegalArgumentException("Funcionario nao existe");
            }
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info("Erro ao excluir funcionario");
            throw new IllegalArgumentException("Erro ao excluir funcionario");
        }
    }

    @Override
    public Funcionario buscaFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        try{
            if(funcionarioRepositorio.existeCpf(cpfEntidade)){
                return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.encontrePorCpf(cpfEntidade));
            } else {
                Logger.getLogger("FUNCIONARIO").info("Funcionario nao existe");
                throw new IllegalArgumentException("Funcionario nao existe");
            }
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info("Erro ao buscar funcionario");
            throw new IllegalArgumentException("Erro ao buscar funcionario");
        }
    }

    @Override
    public boolean existeFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        return funcionarioRepositorio.existeCpf(cpfEntidade);
    }
}
