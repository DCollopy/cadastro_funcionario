package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


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