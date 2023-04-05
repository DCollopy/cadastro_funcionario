package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Embeddable
@Data
@Table(name = "carteira_trabalho")
public class CarteiraTrabalhoEntidade {
    private String numero_trabalho;
}
