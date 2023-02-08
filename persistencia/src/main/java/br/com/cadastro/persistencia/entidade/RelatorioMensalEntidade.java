package br.com.cadastro.persistencia.entidade;

import br.com.cadastro.dominio.entidade.Funcionario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "funcionario_numero_cpf", referencedColumnName = "numero_cpf")
    })
    private FuncionarioEntidade funcionario;
}
