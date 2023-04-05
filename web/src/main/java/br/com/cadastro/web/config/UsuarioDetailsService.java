package br.com.cadastro.web.config;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.dominio.entidade.service.LoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final LoginService loginService;

    public UsuarioDetailsService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginService.buscaPorEmail(email);

        String role;
        if(login == null){
            throw new UsernameNotFoundException("Usuario não existe");
        }
        return UserPrincipal.cadastra(login);
    }
}
