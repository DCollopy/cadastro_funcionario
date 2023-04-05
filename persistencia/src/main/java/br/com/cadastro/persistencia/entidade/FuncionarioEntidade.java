package br.com.cadastro.persistencia.entidade;



import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "funcionario")
public class FuncionarioEntidade extends UsuarioEntidade implements Serializable {

    private CarteiraTrabalhoEntidade carteiraTrabalho;

    private PisEntidade pis;

    private SalarioEntidade salario_bruto;

    private int dependentes = 0;

    private LocalDate data_admissao;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "empresa_numero_cnpj", referencedColumnName = "numero_cnpj")
    })
    private EmpresaEntidade empresa;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "funcionario_relatorio_mensal",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "mensal_id"))
    private Set<RelatorioMensalEntidade> relatoriosMensais;


    public CarteiraTrabalhoEntidade getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(CarteiraTrabalhoEntidade carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public PisEntidade getPis() {
        return pis;
    }

    public void setPis(PisEntidade pis) {
        this.pis = pis;
    }

    public SalarioEntidade getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(SalarioEntidade salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

    public LocalDate getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(LocalDate data_admissao) {
        this.data_admissao = data_admissao;
    }

    public EmpresaEntidade getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntidade empresa) {
        this.empresa = empresa;
    }


    public Set<RelatorioMensalEntidade> getRelatoriosMensais() {
        return relatoriosMensais;
    }

    public void setRelatoriosMensais(Set<RelatorioMensalEntidade> relatoriosMensais) {
        this.relatoriosMensais = relatoriosMensais;
    }
}