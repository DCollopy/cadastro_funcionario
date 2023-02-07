package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.objetos.Cnpj;
import br.com.cadastro.persistencia.entidade.CnpjEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CnpjMapper {
    private final ModelMapper modelMapper;
    public CnpjEntidade converteCnpjToEntidade(Cnpj cnpj) {
        return modelMapper.map(cnpj, CnpjEntidade.class);
    }

    public Cnpj converteEntidadeToCnpj(CnpjEntidade cnpjEntidade) {
        return modelMapper.map(cnpjEntidade, Cnpj.class);
    }
}
