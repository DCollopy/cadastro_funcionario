package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;


import java.io.Serializable;

@Embeddable
@Data
@Table(name = "cpf")
public class CpfEntidade implements Serializable {

    private static final long serialVersionUID = 479402256022370443L;
    private String numero_cpf;
}
