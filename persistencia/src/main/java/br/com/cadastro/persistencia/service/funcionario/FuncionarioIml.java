package br.com.cadastro.persistencia.service.funcionario;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.repositorio.FuncionarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Funcionario editaFuncionario(String cpf) {
        return null;
    }

    @Override
    public List<Funcionario> listaFuncionarios() {
        return null;
    }

    @Override
    public void deletaFuncionario(String cpf) {

    }

    @Override
    public Funcionario buscaFuncionario(String cpf) {
        return null;
    }

    @Override
    public boolean existeFuncionario(String cpf) {
        return false;
    }
}
