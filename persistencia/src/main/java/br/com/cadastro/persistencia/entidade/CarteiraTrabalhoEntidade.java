package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "carteira_trabalho")
public class CarteiraTrabalhoEntidade {
    private String numero_trabalho;
}
