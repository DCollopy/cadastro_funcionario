package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.web.model.FuncionarioDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Funcionario> converteDTOToFuncionarios(List<FuncionarioDTO> funcionarioDTO) {
        return funcionarioDTO.stream().map(this::converteDTOToFuncionario).collect(Collectors.toList());
    }
}
