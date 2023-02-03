package br.com.cadastro.persistencia.entidade;


import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Data
@Table(name = "usuario")
public class UsuarioEntidade {
    private String nome;

    private String sobrenome;

    @EmbeddedId
    private CpfEntidade cpf;

    private EmailEntidade email;

    private TelefoneEntidade telefone;

    private EnderecoEntidade endereco;

    private String cargo;
}
