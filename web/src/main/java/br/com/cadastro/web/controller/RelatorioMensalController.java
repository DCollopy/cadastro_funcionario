package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.RelatorioMensal;
import br.com.cadastro.dominio.entidade.service.RelatorioMensalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/relatorio/mensal")
public class RelatorioMensalController {

    private final RelatorioMensalService relatorioMensalService;

    public RelatorioMensalController(RelatorioMensalService relatorioMensalService) {
        this.relatorioMensalService = relatorioMensalService;
    }

    @GetMapping("/{cpf}")
    public List<RelatorioMensal> relatorioMensalFuncionario(@PathVariable String cpf) {
        return relatorioMensalService.listaRelatorioMensalPorFuncionario(cpf);
    }

    @GetMapping("/funcionario")
    public RelatorioMensal relatorioMensal(@RequestBody String cpf, @RequestBody LocalDate mesRelatorio) {
        return relatorioMensalService.buscarRelatorioMensalPorMes(mesRelatorio, cpf);
    }

    @GetMapping("/gerarExcel/{cpf}")
    public ResponseEntity<byte[]> gerarExcel(@PathVariable String cpf) {
        relatorioMensalService.gerarExcelRelatorio(cpf);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<RelatorioMensal> criaRelatorioMensal(@RequestBody String cpf) {
        relatorioMensalService.criaRelatorioMensal(cpf);
        return ResponseEntity.ok().build();
    }
}