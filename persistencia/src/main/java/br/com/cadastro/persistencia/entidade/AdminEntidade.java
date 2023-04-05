package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


import java.io.Serializable;

@Entity
@Data
@Table(name = "admin")
public class AdminEntidade extends UsuarioEntidade implements Serializable {
    private CnpjEntidade cnpj;

    private String cargo;
}
