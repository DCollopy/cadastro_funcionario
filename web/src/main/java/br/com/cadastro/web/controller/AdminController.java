package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.service.AdminService;
import br.com.cadastro.dominio.usecases.excel.ILerExcel;
import br.com.cadastro.dominio.usecases.excel.LerExcel;
import br.com.cadastro.web.converte.AdminMapperWeb;
import br.com.cadastro.web.model.AdminDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminMapperWeb adminMapperWeb;

    private ILerExcel lerExcel = new LerExcel();

    public AdminController(AdminService adminService, AdminMapperWeb adminMapperWeb) {
        this.adminService = adminService;
        this.adminMapperWeb = adminMapperWeb;
    }

    @GetMapping("/listar")
    public List<AdminDTO> listar() {
        return adminMapperWeb.converteListaAdminToDTO(adminService.listaAdmin());
    }

    @GetMapping("/buscar/{cpf}")
    public AdminDTO buscar(@PathVariable String cpf) {
        return adminMapperWeb.converteAdminToDTO(adminService.buscaAdmin(cpf));
    }

    @PostMapping("/cadastro")
    public ResponseEntity criarAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        Admin admin = adminMapperWeb.converteDTOToAdmin(adminDTO);
        adminService.criaAdmin(admin);
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
            List<Admin> leitrua = lerExcel.leituraExcelAdmin(arquivoFuncionario.getAbsolutePath());
            adminService.criaAdminLote(leitrua);

        } catch (IOException e) {
            return new ResponseEntity < > (HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Sucesso em subir arquivo - " + arquivo.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/editar/{cpf}")
    public ResponseEntity editarAdmin(@PathVariable String cpf, @Valid @RequestBody AdminDTO adminDTO) {
        Admin admin = adminMapperWeb.converteDTOToAdmin(adminDTO);
        if(adminService.buscaAdmin(cpf) != null) {
            adminService.editaAdmin(admin);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity excluirAdmin(@PathVariable String cpf) {
        adminService.excluirAdmin(cpf);
        return ResponseEntity.ok().build();
    }
}