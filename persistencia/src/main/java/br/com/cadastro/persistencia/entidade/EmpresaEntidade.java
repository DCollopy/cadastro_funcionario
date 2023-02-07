package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "empresa")
public class EmpresaEntidade {
    private String razaosocial;
    private String nomefantasia;
    @EmbeddedId
    private CnpjEntidade cnpj;
}
