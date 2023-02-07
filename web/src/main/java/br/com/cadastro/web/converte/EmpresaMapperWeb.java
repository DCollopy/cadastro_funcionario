package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.web.model.EmpresaDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmpresaMapperWeb {
    private final ModelMapper modelMapper;

    public EmpresaDTO converteEmpresaToDTO(Empresa empresa) {
        return modelMapper.map(empresa, EmpresaDTO.class);
    }

    public Empresa converteDTOToEmpresa(EmpresaDTO empresaDTO) {
        return modelMapper.map(empresaDTO, Empresa.class);
    }

    public List<Empresa> converteListaDTOToEmpresa(List<EmpresaDTO> empresaDTOS) {
        return empresaDTOS.stream().map(this::converteDTOToEmpresa).toList();
    }

    public List<EmpresaDTO> converteListaEmpresaToDTO(List<Empresa> empresas) {
        return empresas.stream().map(this::converteEmpresaToDTO).toList();
    }
}
