package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.persistencia.entidade.EmpresaEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmpresaMapper {
    private final ModelMapper modelMapper;

    public EmpresaEntidade converteEmpresaToEntidade(Empresa empresa) {
        return modelMapper.map(empresa, EmpresaEntidade.class);
    }

    public Empresa converteEntidadeToEmpresa(EmpresaEntidade empresaEntidade) {
        return modelMapper.map(empresaEntidade, Empresa.class);
    }

    public List<Empresa> converteListaEntidadeToEmpresa(List<EmpresaEntidade> empresaEntidades) {
        return empresaEntidades.stream().map(this::converteEntidadeToEmpresa).toList();
    }

    public List<EmpresaEntidade> converteListaEmpresaToEntidade(List<Empresa> empresas) {
        return empresas.stream().map(this::converteEmpresaToEntidade).toList();
    }
}
