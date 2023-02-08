package br.com.cadastro.persistencia.service.funcionario;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.objetos.Cnpj;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.persistencia.converter.CnpjMapper;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.repositorio.EmpresaRepositorio;
import br.com.cadastro.persistencia.repositorio.FuncionarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FuncionarioIml implements FuncionarioService {

    private final FuncionarioRepositorio funcionarioRepositorio;

    private final EmpresaRepositorio empresaRepositorio;

    private final FuncionarioMapper funcionarioMapper;

    private final CpfMapper cpfMapper;

    private final CnpjMapper cnpjMapper;

    private FuncionarioValidaAbs funcionarioValidaAbs = new FuncionarioValidaAbs();

    public FuncionarioIml(FuncionarioRepositorio funcionarioRepositorio, EmpresaRepositorio empresaRepositorio, FuncionarioMapper funcionarioMapper, CpfMapper cpfMapper, CnpjMapper cnpjMapper) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.funcionarioMapper = funcionarioMapper;
        this.cpfMapper = cpfMapper;
        this.cnpjMapper = cnpjMapper;
    }

    @Override
    public Funcionario criaFuncionario(Funcionario funcionario, String cnpj) {
        try{
            Cnpj insereCnpj = getCnpj(cnpj);
            Funcionario funcioValida = funcionarioValidaAbs.criaFuncionario(funcionario);
            FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcioValida);
            funcionarioEntidade.setEmpresa(empresaRepositorio.encontrePorCnpj(cnpjMapper.converteCnpjToEntidade(insereCnpj)));
            valida(funcionarioEntidade);
            Funcionario salvo = funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.salvar(funcionarioEntidade));
            empresaRepositorio.encontrePorCnpj(cnpjMapper.converteCnpjToEntidade(insereCnpj)).getFuncionario().add(funcionarioEntidade);
            return salvo;
        }catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao criar funcionario" + e.getMessage());
        }
    }

    @Override
    public List<Funcionario> criaFuncionarios(List<Funcionario> funcionarios, String cnpj) {
        try{
            Cnpj insereCnpj = getCnpj(cnpj);
            for (Funcionario funcionario : funcionarios) {
                Funcionario funcioValida = funcionarioValidaAbs.criaFuncionario(funcionario);
                FuncionarioEntidade funcionarioEntidade = funcionarioMapper.converteFuncionarioToEntidade(funcioValida);
                funcionarioEntidade.setEmpresa(empresaRepositorio.encontrePorCnpj(cnpjMapper.converteCnpjToEntidade(insereCnpj)));
                valida(funcionarioEntidade);
                funcionarioRepositorio.salvar(funcionarioEntidade);
                empresaRepositorio.encontrePorCnpj(cnpjMapper.converteCnpjToEntidade(insereCnpj)).getFuncionario().add(funcionarioEntidade);
            }
            return funcionarios;
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao criar funcionarios" + e.getMessage());
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
            if(!funcionarioRepositorio.existeCpf(cpfEntidade)){
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
            if(!funcionarioRepositorio.existeCpf(cpfEntidade)){
                return funcionarioMapper.converteEntidadeToFuncionario(funcionarioRepositorio.encontrePorCpf(cpfEntidade));
            } else {
                Logger.getLogger("FUNCIONARIO").info("Funcionario nao existe");
                throw new IllegalArgumentException("Funcionario nao existe");
            }
        } catch (Exception e) {
            Logger.getLogger("FUNCIONARIO").info("Erro ao buscar funcionario" + e.getMessage());
            throw new IllegalArgumentException("Erro ao buscar funcionario" + e.getMessage());
        }
    }

    @Override
    public boolean existeFuncionario(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        return funcionarioRepositorio.existeCpf(cpfEntidade);
    }

    private void valida(FuncionarioEntidade funcionarioEntidade) {
        if(funcionarioEntidade.getEmpresa() == null)  throw new IllegalArgumentException("Empresa nao existe");

        if(!funcionarioRepositorio.existeCpf(funcionarioEntidade.getCpf())) throw new IllegalArgumentException("Funcionario ja cadastrado" + funcionarioEntidade.getCpf().getNumero_cpf());
    }

    private Cnpj getCnpj(String cnpj) {
        StringBuilder stringBuilder = new StringBuilder(cnpj);
        stringBuilder.insert(cnpj.length() - 7, '/');
        Cnpj insereCnpj = new Cnpj(stringBuilder.toString());
        return insereCnpj;
    }
}
