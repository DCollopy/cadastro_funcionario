package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Empresa;
import br.com.cadastro.dominio.entidade.service.EmpresaService;
import br.com.cadastro.dominio.usecases.excel.ILerExcel;
import br.com.cadastro.dominio.usecases.excel.LerExcel;
import br.com.cadastro.web.converte.EmpresaMapperWeb;
import br.com.cadastro.web.model.EmpresaDTO;
import jakarta.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;
    private final EmpresaMapperWeb empresaMapperWeb;
    private ILerExcel lerExcel = new LerExcel();

    public EmpresaController(EmpresaService empresaService, EmpresaMapperWeb empresaMapperWeb) {
        this.empresaService = empresaService;
        this.empresaMapperWeb = empresaMapperWeb;
    }

    @GetMapping("/listar")
    public List<EmpresaDTO> listar() {
        return empresaMapperWeb.converteListaEmpresaToDTO(empresaService.listarEmpresas());
    }

    @GetMapping("/buscar/{cnpj}")
    public EmpresaDTO buscar(@PathVariable String cnpj) {
        return empresaMapperWeb.converteEmpresaToDTO(empresaService.encontrePorCnpj(cnpj));
    }

    @GetMapping("/buscar/funcionarios/{cnpj}")
    public ResponseEntity buscarFuncionarios(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.listarFuncionarios(cnpj));
    }
    @PostMapping("/cadastro")
    public ResponseEntity criarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = empresaMapperWeb.converteDTOToEmpresa(empresaDTO);
        empresaService.salvar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/cadastro/lote", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity< ? > uploadFile(@RequestParam("arquivo") MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            return new ResponseEntity("Selecione um arquivo", HttpStatus.OK);
        }
        try {
            File arquivoFuncionario = new File(Objects.requireNonNull(arquivo.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(arquivoFuncionario, arquivo.getBytes());
            List<Empresa> leitura = lerExcel.leituraExcelEmpresa(arquivoFuncionario.getAbsolutePath());
            empresaService.salvarLote(leitura);

        } catch (IOException e) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Sucesso em subir arquivo - " + arquivo.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/excluir/{cnpj}")
    public ResponseEntity excluir(@PathVariable String cnpj) {
        empresaService.excluir(cnpj);
        return ResponseEntity.ok().build();
    }
}