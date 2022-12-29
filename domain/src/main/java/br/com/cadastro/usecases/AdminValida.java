package br.com.cadastro.usecases;

import br.com.cadastro.domain.entities.Admin;

import java.util.logging.Logger;

public abstract class AdminValida {

    public Admin criaAdmin(Admin admin) {
        try {
            Admin adminCria = new Admin(admin.getNome(), admin.getSobrenome()
                    , admin.getCpf(), admin.getEmail()
                    , admin.getTelefone(), admin.getEndereco()
                    , admin.getCnpj());
            Logger.getLogger("ADMIN").info("Admin criado com sucesso");
            return adminCria;
        } catch (Exception e) {
            Logger.getLogger("ADMIN").info("Admin nao criado");
            throw new IllegalArgumentException("Admin nao criado");
        }
    }

    public Admin editaAdmin(Admin admin) {
        try {
            Admin adminAtualiza = new Admin(admin.getNome(), admin.getSobrenome()
                    , admin.getCpf(), admin.getEmail()
                    , admin.getTelefone(), admin.getEndereco()
                    , admin.getCnpj());
            Logger.getLogger("ADMIN").info("Admin atualizado com sucesso");
            return adminAtualiza;
        } catch (Exception e) {
            Logger.getLogger("ADMIN").info("Admin nao atualizado");
            throw new IllegalArgumentException("Admin nao atualizado");
        }
    }
}
