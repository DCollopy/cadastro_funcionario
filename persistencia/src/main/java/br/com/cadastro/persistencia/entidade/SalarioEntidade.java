package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;



@Embeddable
@Data
@Table(name = "salario")
public class SalarioEntidade {
    private double salario_bruto;
}
