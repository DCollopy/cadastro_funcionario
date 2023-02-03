package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.persistencia.entidade.CpfEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfMapper {
    private final ModelMapper modelMapper;

    public CpfEntidade converteCpfToEntidade(Cpf cpf) {
        return modelMapper.map(cpf, CpfEntidade.class);
    }

    public Cpf converteEntidadeToCpf(CpfEntidade cpfEntidade) {
        return modelMapper.map(cpfEntidade, Cpf.class);
    }
}
