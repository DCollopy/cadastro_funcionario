package br.com.cadastro.persistencia.converter;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.persistencia.entidade.AdminEntidade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminMapper {
    private final ModelMapper modelMapper;

    public AdminEntidade converteAdminToEntidade(Admin admin) {
        return modelMapper.map(admin, AdminEntidade.class);
    }

    public Admin converteEntidadeToAdmin(AdminEntidade adminEntidade) {
        return modelMapper.map(adminEntidade, Admin.class);
    }

    public List<Admin> converteEntidadeToAdmin(List<AdminEntidade> adminEntidades) {
        return adminEntidades.stream().map(this::converteEntidadeToAdmin).toList();
    }
}
