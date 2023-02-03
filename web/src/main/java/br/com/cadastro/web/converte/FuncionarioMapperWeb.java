package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.web.model.FuncionarioDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuncionarioMapperWeb {
    private final ModelMapper modelMapper;

    public FuncionarioDTO converteFuncionarioToDTO(Funcionario funcionario) {
        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }

    public Funcionario converteDTOToFuncionario(FuncionarioDTO funcionarioDTO) {
        return modelMapper.map(funcionarioDTO, Funcionario.class);
    }
}
