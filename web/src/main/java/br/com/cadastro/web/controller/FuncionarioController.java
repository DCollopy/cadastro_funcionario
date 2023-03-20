package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Funcionario;

import br.com.cadastro.dominio.entidade.service.FuncionarioService;
import br.com.cadastro.dominio.usecases.excel.ILerExcel;
import br.com.cadastro.dominio.usecases.excel.LerExcel;
import br.com.cadastro.web.converte.FuncionarioMapperWeb;
import br.com.cadastro.web.model.FuncionarioDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    private final FuncionarioMapperWeb funcionarioMapperWeb;

    private ILerExcel lerExcel = new LerExcel();

    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioMapperWeb funcionarioMapperWeb) {
        this.funcionarioService = funcionarioService;
        this.funcionarioMapperWeb = funcionarioMapperWeb;
    }

    @GetMapping("/listar")
    public List<FuncionarioDTO> listar() {
        return funcionarioMapperWeb.converteDTOToFuncionarios(funcionarioService.listaFuncionarios());
    }

    @GetMapping("/buscar/{cpf}")
    public FuncionarioDTO buscar(@PathVariable String cpf) {
        return funcionarioMapperWeb.converteFuncionarioToDTO(funcionarioService.buscaFuncionario(cpf));
    }

    @PostMapping("/cadastro/{cnpj}")
    public Funcionario criarFuncionario(@Valid @PathVariable String cnpj,@RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioMapperWeb.converteDTOToFuncionario(funcionarioDTO);
        return funcionarioService.criaFuncionario(funcionario,cnpj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/cadastro/lote/{cnpj}", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity< ? > uploadFile(@PathVariable String cnpj,@RequestParam("arquivo") MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            return new ResponseEntity("Selecione um arquivo", HttpStatus.OK);
        }
        try {

            File arquivoFuncionario = new File(Objects.requireNonNull(arquivo.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(arquivoFuncionario, arquivo.getBytes());
            List<Funcionario> leitura = lerExcel.leituraExcel(arquivoFuncionario.getAbsolutePath());
            funcionarioService.criaFuncionarios(leitura,cnpj);

        } catch (IOException e) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Sucesso ao subir arquivo - " + arquivo.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/editar/{cpf}")
    public ResponseEntity editarFuncionario(@PathVariable String cpf, @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioMapperWeb.converteDTOToFuncionario(funcionarioDTO);
        if(funcionarioService.existeFuncionario(cpf)) {
            funcionarioService.editaFuncionario(funcionario);
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity excluirFuncionario(@PathVariable String cpf) {
        funcionarioService.excluirFuncionario(cpf);
        return ResponseEntity.ok().build();
    }
}