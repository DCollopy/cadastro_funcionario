package br.com.cadastro.persistencia.entidade;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "empresa")
public class EmpresaEntidade {
    private String razaosocial;
    private String nomefantasia;
    @EmbeddedId
    private CnpjEntidade cnpj;

    @OneToMany(mappedBy = "empresa")
    private Set<FuncionarioEntidade> funcionario;

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public CnpjEntidade getCnpj() {
        return cnpj;
    }

    public void setCnpj(CnpjEntidade cnpj) {
        this.cnpj = cnpj;
    }

    public Set<FuncionarioEntidade> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Set<FuncionarioEntidade> funcionario) {
        this.funcionario = funcionario;
    }
}
