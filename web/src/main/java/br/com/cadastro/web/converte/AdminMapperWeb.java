package br.com.cadastro.web.converte;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.web.model.AdminDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminMapperWeb {
    private final ModelMapper modelMapper;

    public AdminDTO converteAdminToDTO(Admin admin) {
        return modelMapper.map(admin, AdminDTO.class);
    }

    public Admin converteDTOToAdmin(AdminDTO adminDTO) {
        return modelMapper.map(adminDTO, Admin.class);
    }

    public List<Admin> converteListaDTOToAdmin(List<AdminDTO> adminDTOS) {
        return adminDTOS.stream().map(this::converteDTOToAdmin).toList();
    }

    public List<AdminDTO> converteListaAdminToDTO(List<Admin> admins) {
        return admins.stream().map(this::converteAdminToDTO).toList();
    }
}
