package br.com.cadastro.persistencia.service.login;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.objetos.Email;
import br.com.cadastro.dominio.entidade.service.LoginService;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.EmailMapper;
import br.com.cadastro.persistencia.converter.LoginMapper;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import br.com.cadastro.persistencia.entidade.EmailEntidade;
import br.com.cadastro.persistencia.repositorio.LoginRepositorio;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoginIml implements LoginService {

    private final CpfMapper cpfMapper;

    private final LoginMapper loginMapper;

    private final EmailMapper emailMapper;

    private final LoginRepositorio loginRepositorio;

    public LoginIml(CpfMapper cpfMapper, LoginMapper loginMapper, EmailMapper emailMapper, LoginRepositorio loginRepositorio) {
        this.cpfMapper = cpfMapper;
        this.loginMapper = loginMapper;
        this.emailMapper = emailMapper;
        this.loginRepositorio = loginRepositorio;
    }


    @Override
    public void salva(Login login) {
        try {
            if(existeCpf(login) && existeEmail(login)){
                loginRepositorio.salvar(loginMapper.converteLoginToEntidade(login));
                Logger.getLogger("LOGIN").info("Usuario salvo com sucesso");
            } else{
                Logger.getLogger("LOGIN").info("Usuario ja existe");
            }
        } catch (Exception e) {
            Logger.getLogger("LOGIN").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao salvar usuario" + e.getMessage());
        }
    }

    private Boolean existeCpf(Login login) {
        return loginRepositorio.existePorCpf(cpfMapper.converteCpfToEntidade(login.getCpf()));
    }

    private Boolean existeEmail(Login login) {
        return loginRepositorio.existePorEmail(emailMapper.converteEmailToEntidade(login.getEmail()));
    }

    @Override
    public Login buscaPorCpf(String cpf) {
        try {
            CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(new Cpf(cpf));
            return loginMapper.converteEntidadeToLogin(loginRepositorio.buscarPorCpf(cpfEntidade));
        }
        catch (Exception e) {
            Logger.getLogger("LOGIN").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao buscar usuario" + e.getMessage());
        }
    }

    @Override
    public Login buscaPorEmail(String email) {
        try {
            EmailEntidade emailEntidade = emailMapper.converteEmailToEntidade(new Email(email));
            return loginMapper.converteEntidadeToLogin(loginRepositorio.buscarPorEmail(emailEntidade));
        }
        catch (Exception e) {
            Logger.getLogger("LOGIN").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao buscar usuario" + e.getMessage());
        }
    }

    @Override
    public void excluir(String cpf) {
        try {
            CpfEntidade cpfEntidade = cpfMapper.converteCpfToEntidade(new Cpf(cpf));
            if (loginRepositorio.existePorCpf(cpfEntidade)) {
                loginRepositorio.excluir(loginRepositorio.buscarPorCpf(cpfEntidade));
            } else {
                Logger.getLogger("LOGIN").info("Usuario não existe");
            }
        }
        catch (Exception e) {
            Logger.getLogger("LOGIN").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao excluir usuario" + e.getMessage());
        }
    }

    @Override
    public Login fazerLogin(String email, String senha) {
        try {
            EmailEntidade emailEntidade = emailMapper.converteEmailToEntidade(new Email(email));
            return loginMapper.converteEntidadeToLogin(loginRepositorio.fazerLogin(emailEntidade, senha));
        }
        catch (Exception e) {
            Logger.getLogger("LOGIN").info(e.getMessage());
            throw new IllegalArgumentException("Erro ao fazer login" + e.getMessage());
        }
    }

}
