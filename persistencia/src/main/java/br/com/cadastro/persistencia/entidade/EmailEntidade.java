package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "email")
public class EmailEntidade {

    private String endereco_email;
}
