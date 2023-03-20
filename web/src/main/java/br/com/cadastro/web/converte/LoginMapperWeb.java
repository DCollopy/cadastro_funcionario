package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.Login;
import br.com.cadastro.web.model.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginMapperWeb {
    private final ModelMapper modelMapper;

    public LoginDTO converteLoginParaLoginDTO(Login login) {
        return modelMapper.map(login, LoginDTO.class);
    }

    public Login converteLoginDTOParaLogin(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Login.class);
    }
}
