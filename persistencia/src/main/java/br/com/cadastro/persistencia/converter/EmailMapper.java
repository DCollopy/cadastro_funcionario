package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.objetos.Email;
import br.com.cadastro.persistencia.entidade.EmailEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailMapper {
    private final ModelMapper modelMapper;

    public EmailEntidade converteEmailToEntidade(Email email) {
        return modelMapper.map(email, EmailEntidade.class);
    }

    public Email converteEntidadeToEmail(EmailEntidade emailEntidade) {
        return modelMapper.map(emailEntidade, Email.class);
    }
}
