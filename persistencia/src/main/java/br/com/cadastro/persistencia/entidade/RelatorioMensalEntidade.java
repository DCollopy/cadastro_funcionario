package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "relatorio_mensal")
public class RelatorioMensalEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String titulo;
    private LocalDate mesRelatorio;
    private double descontoIrrf;
    private double descontoInss;
    private double descontoTotal;
    private double salarioLiquido;
    private double outrosDescontos;
    private String observacoes;

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(name = "relatorio_mensal_funcionario",
            joinColumns = @JoinColumn(name = "relatorio_mensal_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private Set<FuncionarioEntidade> funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getMesRelatorio() {
        return mesRelatorio;
    }

    public void setMesRelatorio(LocalDate mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public double getDescontoIrrf() {
        return descontoIrrf;
    }

    public void setDescontoIrrf(double descontoIrrf) {
        this.descontoIrrf = descontoIrrf;
    }

    public double getDescontoInss() {
        return descontoInss;
    }

    public void setDescontoInss(double descontoInss) {
        this.descontoInss = descontoInss;
    }

    public double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public double getOutrosDescontos() {
        return outrosDescontos;
    }

    public void setOutrosDescontos(double outrosDescontos) {
        this.outrosDescontos = outrosDescontos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Set<FuncionarioEntidade> getFuncionario() {
        return funcionario;
    }

    public void setFuncionarios(Set<FuncionarioEntidade> funcionarios) {
        this.funcionario = funcionarios;
    }
}
