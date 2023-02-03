package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "cep")
public class CepEntidade {
    private String cep;
}
