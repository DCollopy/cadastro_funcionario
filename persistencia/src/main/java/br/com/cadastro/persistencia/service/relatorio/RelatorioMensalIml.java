package br.com.cadastro.persistencia.service.relatorio;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.dominio.entidade.objetos.Cpf;
import br.com.cadastro.dominio.entidade.service.RelatorioMensalService;
import br.com.cadastro.dominio.usecases.excel.GerarExcelSalario;
import br.com.cadastro.dominio.usecases.excel.IGerarExcelSalario;
import br.com.cadastro.persistencia.converter.CpfMapper;
import br.com.cadastro.persistencia.converter.FuncionarioMapper;
import br.com.cadastro.persistencia.converter.RelatorioMensalMapper;
import br.com.cadastro.persistencia.entidade.FuncionarioEntidade;
import br.com.cadastro.persistencia.entidade.RelatorioMensalEntidade;
import br.com.cadastro.persistencia.repositorio.FuncionarioRepositorio;
import br.com.cadastro.persistencia.repositorio.RelatorioMensalRepositorio;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class RelatorioMensalIml implements RelatorioMensalService {
    private final RelatorioMensalRepositorio relatorioMensalRepositorio;
    private final RelatorioMensalMapper relatorioMensalMapper;
    private final RelatorioMensalValidaAbs relatorioMensalValidaAbs = new RelatorioMensalValidaAbs();
    private final FuncionarioRepositorio funcionarioRepositorio;
    private final FuncionarioMapper funcionarioMapper;
    private final CpfMapper cpfMapper;
    private final IGerarExcelSalario gerarExcelSalario = new GerarExcelSalario();

    public RelatorioMensalIml(RelatorioMensalRepositorio relatorioMensalRepositorio, RelatorioMensalMapper relatorioMensalMapper, FuncionarioRepositorio funcionarioRepositorio, FuncionarioMapper funcionarioMapper, CpfMapper cpfMapper) {
        this.relatorioMensalRepositorio = relatorioMensalRepositorio;
        this.relatorioMensalMapper = relatorioMensalMapper;
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.funcionarioMapper = funcionarioMapper;
        this.cpfMapper = cpfMapper;
    }
    @Override
    public void criaRelatorioMensal(String cpf) {
        RelatorioMensalEntidade relatorioMensalEntidade = new RelatorioMensalEntidade();

        try {
            FuncionarioEntidade funcionarioEntidade = funcionario(cpf);
            Set<Funcionario> funcionario = Set.of(funcionarioMapper.converteEntidadeToFuncionario(funcionarioEntidade));
            if (funcionarioEntidade == null) {
                Logger.getLogger("Funcionario não encontrado");
                throw new IllegalArgumentException("Funcionário não encontrado");
            }else {
                RelatorioMensal relatorioMensal = relatorioMensalMapper.converteEntidadeToRelatorioMensal(relatorioMensalEntidade);
                relatorioMensal.setFuncionario(funcionario);
                RelatorioMensal valida = relatorioMensalValidaAbs.criaRelatorioMensal(relatorioMensal);

                relatorioMensalRepositorio.salvarRelatorioMensal(relatorioMensalMapper.converteRelatorioMensalToEntidade(valida));
                funcionarioEntidade.getRelatoriosMensais().add(relatorioMensalMapper.converteRelatorioMensalToEntidade(valida));

                funcionarioRepositorio.salvar(funcionarioEntidade);
            }
        }catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao gerar o relatorio" + e.getMessage());
            throw new IllegalArgumentException("Erro ao gerar o relatorio" + e.getMessage());
        }
    }

    private FuncionarioEntidade funcionario(String cpf) {
        Cpf cpfFuncionario = new Cpf(cpf);
        FuncionarioEntidade funcionario = funcionarioRepositorio.encontrePorCpf(cpfMapper.converteCpfToEntidade(cpfFuncionario));
        return funcionario;
    }

    @Override
    public RelatorioMensal editaRelatorioMensal(RelatorioMensal relatorioMensal) {
        return null;
    }

    @Override
    public List<RelatorioMensal> listaRelatorioMensalPorFuncionario(String cpf) {
        FuncionarioEntidade funcionario = funcionario(cpf);
        if(funcionario != null) {
            return relatorioMensalMapper.converteEntidadeToRelatorioMensal(funcionario.getRelatoriosMensais().stream().toList());
        }else {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
    }
    @Override
    public RelatorioMensal buscarRelatorioMensalPorMes(LocalDate mesRelatorio, String cpf) {
        try{
            FuncionarioEntidade funcionario = funcionario(cpf);
            if(mesRelatorio == null) {
                throw new IllegalArgumentException("Mês não pode ser nulo");
            }
            if(funcionario != null) {
                RelatorioMensalEntidade resultado = relatorioMensalRepositorio.buscarRelatorioMensalPorMes(mesRelatorio, funcionario);
                return relatorioMensalMapper.converteEntidadeToRelatorioMensal(resultado);
            }else {
                throw new IllegalArgumentException("Funcionário não encontrado");
            }
        }catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao buscar o relatorio" + e.getMessage());
            throw new IllegalArgumentException("Erro ao buscar o relatorio" + e.getMessage());
        }
    }

    @Override
    public void gerarExcelRelatorio(String cpf) {
        try {
            LocalDate mesRelatorio = LocalDate.now();
            FuncionarioEntidade funcionario = funcionario(cpf);
            String FILE_NAME = "RelatorioMensal.xlsx";
            String fileLocation = new File("src/test/java/br/com/cadastro/teste/" + FILE_NAME).getAbsolutePath();
            if(funcionario != null) {
                RelatorioMensalEntidade resultado = funcionario.getRelatoriosMensais().stream().filter(relatorioMensalEntidade -> relatorioMensalEntidade.getMesRelatorio().equals(mesRelatorio)).findFirst().orElse(null);
                assert resultado != null;
                if(resultado.getFuncionario().isEmpty()){
                    resultado.getFuncionario().add(funcionario);
                    relatorioMensalRepositorio.salvarRelatorioMensal(resultado);
                }
                RelatorioMensal relatorioMensal = relatorioMensalMapper.converteEntidadeToRelatorioMensal(resultado);
                gerarExcelSalario.gerarExcelSalario(relatorioMensal, fileLocation);
            }else {
                throw new IllegalArgumentException("Funcionário não encontrado");
            }
        }catch (Exception e) {
            Logger.getLogger("RELATORIO MENSAL").info("Erro ao gerar o relatorio" + e.getMessage());
            throw new IllegalArgumentException("Erro ao gerar o relatorio" + e.getMessage());
        }
    }
}