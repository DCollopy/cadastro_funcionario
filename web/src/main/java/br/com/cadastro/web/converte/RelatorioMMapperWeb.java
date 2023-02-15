package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.web.model.RelatorioMensalDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelatorioMMapperWeb {
    private final ModelMapper modelMapper;

    public RelatorioMensalDTO converteRelatorioToDTO(RelatorioMensal relatorio) {
        return modelMapper.map(relatorio,  RelatorioMensalDTO.class);
    }

    public RelatorioMensal converteDTOToRelatorio( RelatorioMensalDTO relatorioDTO) {
        return modelMapper.map(relatorioDTO, RelatorioMensal.class);
    }

    public List< RelatorioMensalDTO> converteDTOToRelatorios(List<RelatorioMensal> relatorio) {
        return relatorio.stream().map(this::converteRelatorioToDTO).collect(Collectors.toList());
    }
}