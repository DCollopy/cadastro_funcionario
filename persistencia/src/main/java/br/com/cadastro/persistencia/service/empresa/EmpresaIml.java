package br.com.cadastro.persistencia.service.empresa;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.objetos.Cnpj;
import br.com.cadastro.dominio.entidade.service.EmpresaService;
import br.com.cadastro.persistencia.converter.CnpjMapper;
import br.com.cadastro.persistencia.converter.EmpresaMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.entidade.CnpjEntidade;
import br.com.cadastro.persistencia.repositorio.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmpresaIml implements EmpresaService {
    private final EmpresaRepositorio empresaRepositorio;
    private final EmpresaMapper empresaMapper;

    private final CnpjMapper cnpjMapper;

    private final FuncionarioMapper funcionarioMapper;

    private EmpresaValidaAbs empresaValidaAbs = new EmpresaValidaAbs();

    public EmpresaIml(EmpresaRepositorio empresaRepositorio, EmpresaMapper empresaMapper
            , CnpjMapper cnpjMapper, FuncionarioMapper funcionarioMapper) {
        this.empresaRepositorio = empresaRepositorio;
        this.empresaMapper = empresaMapper;
        this.cnpjMapper = cnpjMapper;
        this.funcionarioMapper = funcionarioMapper;
    }

    @Override
    public void salvar(Empresa empresa) {
        Empresa empresaValida = empresaValidaAbs.criaEmpresa(empresa);
        try {
            if(existePorCnpj(empresa.getCnpj().getNumero_cnpj()))
                empresaRepositorio.salvar(empresaMapper.converteEmpresaToEntidade(empresaValida));
            else throw new RuntimeException("Empresa já cadastrada");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar empresa" + e.getMessage());
        }
    }

    @Override
    public Empresa encontrePorCnpj(String cnpj) {
        Cnpj insereCnpj = getCnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(insereCnpj);
        return empresaMapper.converteEntidadeToEmpresa(empresaRepositorio.encontrePorCnpj(cnpjEntidade));
    }

    @Override
    public List<Funcionario> listarFuncionarios(String cnpj) {
        Cnpj insereCnpj = getCnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(insereCnpj);
        return funcionarioMapper.converteEntidadeToFuncionario(empresaRepositorio.encontreFuncionario(cnpjEntidade));
    }

    @Override
    public Boolean existePorCnpj(String cnpj) {
        Cnpj insereCnpj = getCnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(insereCnpj);
        return empresaRepositorio.existeCnpj(cnpjEntidade);
    }

    @Override
    public void salvarLote(List<Empresa> empresas) {
        try {
            for (Empresa empresa : empresas) {
                Empresa empresaValida = empresaValidaAbs.criaEmpresa(empresa);
                if(existePorCnpj(empresa.getCnpj().getNumero_cnpj()))
                    empresaRepositorio.salvar(empresaMapper.converteEmpresaToEntidade(empresaValida));
                else throw new RuntimeException("Empresa já cadastrada");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar lote de empresas" + e.getMessage());
        }
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return empresaMapper.converteListaEntidadeToEmpresa(empresaRepositorio.listarEmpresas());
    }

    @Override
    public Empresa editar(Empresa empresa) {
        return null;
    }

    @Override
    public void excluir(String cnpj) {
        Cnpj insereCnpj = getCnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(insereCnpj);
        try {
            empresaRepositorio.excluir(cnpjEntidade);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir empresa: " + e.getMessage());
        }
    }

    private Cnpj getCnpj(String cnpj) {
        StringBuilder stringBuilder = new StringBuilder(cnpj);
        stringBuilder.insert(cnpj.length() - 7, '/');
        Cnpj insereCnpj = new Cnpj(stringBuilder.toString());
        return insereCnpj;
    }
}
