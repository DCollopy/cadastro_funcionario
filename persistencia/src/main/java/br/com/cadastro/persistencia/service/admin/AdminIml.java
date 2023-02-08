package br.com.cadastro.persistencia.service.admin;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.service.AdminService;
import br.com.cadastro.persistencia.converter.AdminMapper;
import br.com.cadastro.persistencia.converter.CnpjMapper;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.repositorio.AdminRepositorio;
import br.com.cadastro.persistencia.repositorio.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AdminIml implements AdminService {

    private final AdminRepositorio adminRepositorio;
    private final EmpresaRepositorio empresaRepositorio;
    private final AdminMapper adminMapper;
    private final CpfMapper cpfMapper;
    private final CnpjMapper cnpjMapper;
    private AdminValidaAbs adminValidaAbs = new AdminValidaAbs();

    public AdminIml(AdminRepositorio adminRepositorio, EmpresaRepositorio empresaRepositorio, AdminMapper adminMapper, CpfMapper cpfMapper, CnpjMapper cnpjMapper) {
        this.adminRepositorio = adminRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.adminMapper = adminMapper;
        this.cpfMapper = cpfMapper;
        this.cnpjMapper = cnpjMapper;
    }
    @Override
    public void criaAdmin(Admin admin) {
        try {
            Admin adminValida = adminValidaAbs.criaAdmin(admin);
            if(adminRepositorio.existeCpf(cpfMapper.converteCpfToEntidade(adminValida.getCpf()))
                    && empresaRepositorio.existeCnpj(cnpjMapper.converteCnpjToEntidade(admin.getCnpj()))) throw new RuntimeException("CPF já cadastrado");
            adminRepositorio.salvar(adminMapper.converteAdminToEntidade(adminValida));
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
    }

    @Override
    public void criaAdminLote(List<Admin> admins) {
        try {
            for (Admin admin : admins) {
                Admin adminValida = adminValidaAbs.criaAdmin(admin);
                if(adminRepositorio.existeCpf(cpfMapper.converteCpfToEntidade(adminValida.getCpf()))
                        && empresaRepositorio.existeCnpj(cnpjMapper.converteCnpjToEntidade(admin.getCnpj()))) throw new RuntimeException("CNPJ já cadastrado");
                adminRepositorio.salvar(adminMapper.converteAdminToEntidade(adminValida));
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
    }

    @Override
    public Admin editaAdmin(Admin admin) {
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(admin.getCpf());
        if(adminRepositorio.existeCpf(cpfEntidade)) {
            Admin adminValida = adminValidaAbs.editaAdmin(admin);
            return adminMapper.converteEntidadeToAdmin(adminRepositorio.editar(adminMapper.converteAdminToEntidade(adminValida)));
        }
        Logger.getLogger("ADMIN WEB").info("Admin não encontrado");
        throw new IllegalArgumentException("Admin não encontrado");
    }

    @Override
    public Admin buscaAdmin(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        if(adminRepositorio.existeCpf(cpfEntidade)){
            return adminMapper.converteEntidadeToAdmin(adminRepositorio.encontrePorCpf(cpfEntidade));
        }
        Logger.getLogger("ADMIN WEB").info("Admin não encontrado");
        throw new IllegalArgumentException("Admin não encontrado");
    }

    @Override
    public List<Admin> listaAdmin() {
        return adminMapper.converteEntidadeToAdmin(adminRepositorio.listaAdmins());
    }

    @Override
    public void excluirAdmin(String cpf) {
        Cpf novo = new Cpf(cpf);
        CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(novo);
        try {
            if(adminRepositorio.existeCpf(cpfEntidade)){
                adminRepositorio.excluir(cpfEntidade);
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
    }
}
