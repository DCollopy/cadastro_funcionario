package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@Table(name = "cnpj")
public class CnpjEntidade implements Serializable {
    private String numero_cnpj;
}
