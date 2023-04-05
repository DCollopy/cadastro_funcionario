package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Embeddable
@Data
@Table(name = "cep")
public class CepEntidade {
    private String cep;
}
