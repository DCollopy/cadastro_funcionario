package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "empresa")
public class EmpresaEntidade {
    private String razaosocial;
    private String nomefantasia;
    @EmbeddedId
    private CnpjEntidade cnpj;

    @OneToMany(mappedBy = "empresa")
    private Set<FuncionarioEntidade> funcionario;
}
