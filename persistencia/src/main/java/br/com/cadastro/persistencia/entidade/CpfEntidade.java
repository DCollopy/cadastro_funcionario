package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Data
@Table(name = "cpf")
public class CpfEntidade implements Serializable {

    private static final long serialVersionUID = 479402256022370443L;
    private String numero_cpf;
}
