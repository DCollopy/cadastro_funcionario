package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Embeddable
@Data
@Table(name = "pis")
public class PisEntidade {

    private String numero_pis;
}
