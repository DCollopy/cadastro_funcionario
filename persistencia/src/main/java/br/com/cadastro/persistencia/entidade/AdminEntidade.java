package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "admin")
public class AdminEntidade extends UsuarioEntidade implements Serializable {
    private CnpjEntidade cnpj;

    private String cargo;
}
