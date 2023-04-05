package br.com.cadastro.persistencia.entidade;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;



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
