package br.com.cadastro.dominio.entidade;

import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.objetos.Email;
import lombok.Data;

@Data
public class Login {

    private Cpf cpf;
    private Email email;
    private String senha;

    private String cargo;

    public Login(Cpf cpf, Email email, String senha, String cargo) {
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Login(){}
}
