package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.persistencia.entidade.RelatorioMensalEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelatorioMensalMapper {
    private final ModelMapper modelMapper;

    public RelatorioMensalEntidade converteRelatorioMensalToEntidade(RelatorioMensal relatorioMensal) {
        return modelMapper.map(relatorioMensal, RelatorioMensalEntidade.class);
    }

    public RelatorioMensal converteEntidadeToRelatorioMensal(RelatorioMensalEntidade relatorioMensalEntidade) {
        return modelMapper.map(relatorioMensalEntidade, RelatorioMensal.class);
    }

    public List<RelatorioMensal> converteEntidadeToRelatorioMensal(List<RelatorioMensalEntidade> relatorioMensalEntidade) {
        return relatorioMensalEntidade.stream().map(this::converteEntidadeToRelatorioMensal).collect(Collectors.toList());
    }

    public List<RelatorioMensalEntidade> converteRelatorioMensalToEntidade(List<RelatorioMensal> relatorioMensal) {
        return relatorioMensal.stream().map(this::converteRelatorioMensalToEntidade).collect(Collectors.toList());
    }
}
