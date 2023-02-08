package br.com.cadastro.dominio.entidade;

import br.com.cadastro.dominio.entidade.objetos.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.logging.Logger;

@Data
@SuperBuilder
public class Admin extends Usuario {
    private Cnpj cnpj;

    private final String cargo = "Admin";

    public Admin(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco, Cnpj cnpj) {
        super(nome, sobrenome, cpf, email, telefone, endereco);
        if(cnpj == null){
            Logger.getLogger("ADMIN").info("CNPJ invalido");
            throw new IllegalArgumentException("CNPJ invalido");
        }
        this.cnpj = cnpj;
    }

    @Override
    public String getCargo(String cargo) {
        return this.cargo;
    }
}