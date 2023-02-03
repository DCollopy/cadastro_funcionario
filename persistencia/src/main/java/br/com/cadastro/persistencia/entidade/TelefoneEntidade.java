package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "telefone")
public class TelefoneEntidade {
    private String ddd;

    private String numero_telefone;

}
