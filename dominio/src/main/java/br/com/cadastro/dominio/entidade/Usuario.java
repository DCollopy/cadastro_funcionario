package br.com.cadastro.dominio.entidade;

import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.objetos.Email;
import br.com.cadastro.dominio.entidade.objetos.Endereco;
import br.com.cadastro.dominio.entidade.objetos.Telefone;
import lombok.Data;

import java.util.logging.Logger;

@Data
public abstract class Usuario {
    private Cpf cpf;
    private String nome;

    private String sobrenome;

    private Email email;

    private Telefone telefone;

    private Endereco endereco;

    private String cargo;
    public Usuario() {}
    public Usuario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco) {
        if(nome == null || nome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Nome invalido");
            throw new IllegalArgumentException("Nome invalido");
        }
        if(sobrenome == null || sobrenome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Sobrenome invalido");
            throw new IllegalArgumentException("Sobrenome invalido");
        }
        if(cpf == null){
            Logger.getLogger("USUARIO").info("CPF invalido");
            throw new IllegalArgumentException("CPF invalido");
        }
        if(email == null){
            Logger.getLogger("USUARIO").info("Email invalido");
            throw new IllegalArgumentException("Email invalido");
        }
        if(telefone == null){
            Logger.getLogger("USUARIO").info("Telefone invalido");
            throw new IllegalArgumentException("Telefone invalido");
        }
        if(endereco == null){
            Logger.getLogger("USUARIO").info("Endereco invalido");
            throw new IllegalArgumentException("Endereco invalido");
        }

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Usuario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco, String cargo) {
        if(nome == null || nome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Nome invalido");
            throw new IllegalArgumentException("Nome invalido");
        }
        if(sobrenome == null || sobrenome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Sobrenome invalido");
            throw new IllegalArgumentException("Sobrenome invalido");
        }
        if(cpf == null){
            Logger.getLogger("USUARIO").info("CPF invalido");
            throw new IllegalArgumentException("CPF invalido");
        }
        if(email == null){
            Logger.getLogger("USUARIO").info("Email invalido");
            throw new IllegalArgumentException("Email invalido");
        }
        if(telefone == null){
            Logger.getLogger("USUARIO").info("Telefone invalido");
            throw new IllegalArgumentException("Telefone invalido");
        }
        if(endereco == null){
            Logger.getLogger("USUARIO").info("Endereco invalido");
            throw new IllegalArgumentException("Endereco invalido");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cargo = cargo;
    }

    public abstract String getCargo(String cargo);
}