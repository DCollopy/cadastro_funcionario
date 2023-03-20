package br.com.cadastro.web.config;

import br.com.cadastro.dominio.entidade.Login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Login login) {
        this.email = login.getEmail().getEndereco_email();
        this.senha = login.getSenha();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(login.getCargo());
        authorities = List.of(simpleGrantedAuthority);

    }

    public static UserPrincipal cadastra(Login login){
        return new UserPrincipal(login);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
