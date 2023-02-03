package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuncionarioMapper {
    private final ModelMapper modelMapper;

    public FuncionarioEntidade converteFuncionarioToEntidade(Funcionario funcionario) {
        return modelMapper.map(funcionario, FuncionarioEntidade.class);
    }

    public Funcionario converteEntidadeToFuncionario(FuncionarioEntidade funcionarioEntidade) {
        return modelMapper.map(funcionarioEntidade, Funcionario.class);
    }
}
