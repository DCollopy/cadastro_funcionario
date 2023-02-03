package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Funcionario;
import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.web.converte.FuncionarioMapperWeb;
import br.com.cadastro.web.model.FuncionarioDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cadastro/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    private final FuncionarioMapperWeb funcionarioMapperWeb;
    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioMapperWeb funcionarioMapperWeb) {
        this.funcionarioService = funcionarioService;
        this.funcionarioMapperWeb = funcionarioMapperWeb;
    }

    @PostMapping("/criar/funcionario")
    public Funcionario createFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioMapperWeb.converteDTOToFuncionario(funcionarioDTO);
        return funcionarioService.criaFuncionario(funcionario);
    }
}
