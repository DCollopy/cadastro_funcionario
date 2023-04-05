package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Embeddable
@Data
@Table(name = "telefone")
public class TelefoneEntidade {
    private String ddd;

    private String numero_telefone;

}
