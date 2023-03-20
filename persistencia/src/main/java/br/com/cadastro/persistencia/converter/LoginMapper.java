package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.persistencia.entidade.LoginEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginMapper {

    private final ModelMapper modelMapper;

    public LoginEntidade converteLoginToEntidade(Login login) {
        return modelMapper.map(login, LoginEntidade.class);
    }

    public Login converteEntidadeToLogin(LoginEntidade loginEntidade) {
        return modelMapper.map(loginEntidade, Login.class);
    }
}
