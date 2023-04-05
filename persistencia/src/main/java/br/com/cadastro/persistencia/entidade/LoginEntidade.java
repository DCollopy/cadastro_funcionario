package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "login")
public class LoginEntidade {
    @EmbeddedId
    private CpfEntidade cpf;
    private EmailEntidade email;
    private String senha;
    private String cargo;
}