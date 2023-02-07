package br.com.cadastro.persistencia.service.empresa;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.dominio.entidade.objetos.Cnpj;
import br.com.cadastro.dominio.entidade.service.EmpresaService;
import br.com.cadastro.persistencia.converter.CnpjMapper;
import br.com.cadastro.persistencia.converter.EmpresaMapper;
import br.com.cadastro.persistencia.entidade.CnpjEntidade;
import br.com.cadastro.persistencia.repositorio.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaIml implements EmpresaService {
    private final EmpresaRepositorio empresaRepositorio;
    private final EmpresaMapper empresaMapper;

    private final CnpjMapper cnpjMapper;

    private EmpresaValidaAbs empresaValidaAbs = new EmpresaValidaAbs();

    public EmpresaIml(EmpresaRepositorio empresaRepositorio, EmpresaMapper empresaMapper, CnpjMapper cnpjMapper) {
        this.empresaRepositorio = empresaRepositorio;
        this.empresaMapper = empresaMapper;
        this.cnpjMapper = cnpjMapper;
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
        Cnpj cria = new Cnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(cria);
        return empresaMapper.converteEntidadeToEmpresa(empresaRepositorio.encontrePorCnpj(cnpjEntidade));
    }

    @Override
    public Boolean existePorCnpj(String cnpj) {
        Cnpj cria = new Cnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(cria);
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
        Cnpj cria = new Cnpj(cnpj);
        CnpjEntidade cnpjEntidade = cnpjMapper.converteCnpjToEntidade(cria);
        try {
            empresaRepositorio.excluir(cnpjEntidade);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir empresa: " + e.getMessage());
        }
    }
}
