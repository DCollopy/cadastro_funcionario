package br.com.cadastro.web.controller;

import br.com.cadastro.dominio.entidade.Admin;
import br.com.cadastro.dominio.entidade.service.AdminService;
import br.com.cadastro.dominio.usecases.excel.ILerExcel;
import br.com.cadastro.dominio.usecases.excel.LerExcel;
import br.com.cadastro.web.converte.AdminMapperWeb;
import br.com.cadastro.web.model.AdminDTO;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import jakarta.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RequestMapping("/api/admin")
@Timed("admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminMapperWeb adminMapperWeb;
    private final MeterRegistry meterRegistry;
    private ILerExcel lerExcel = new LerExcel();

    private static final Log logger = LogFactory.getLog(AdminController.class);

    public AdminController(AdminService adminService, AdminMapperWeb adminMapperWeb, MeterRegistry meterRegistry) {
        this.adminService = adminService;
        this.adminMapperWeb = adminMapperWeb;
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/listar")
    public List<AdminDTO> listar() {
        logger.info("Listando todos os admins");
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
    @Timed(value = "admin.time", description = "Tempo de retorno do cadastro em lote",  longTask = true)
    public ResponseEntity< ? > uploadFile(@RequestParam("arquivo") MultipartFile arquivo) {
        if (arquivo.isEmpty()) {
            return new ResponseEntity("Selecione um arquivo", HttpStatus.OK);
        }
        try {
            meterRegistry.counter("admin_cadastro_lote", "cadastro_admin", "/cadastro/lote").increment();
            File arquivoFuncionario = new File(Objects.requireNonNull(arquivo.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(arquivoFuncionario, arquivo.getBytes());
            List<Admin> leitrua = lerExcel.leituraExcelAdmin(arquivoFuncionario.getAbsolutePath());
            for (Admin admin : leitrua) {
                logger.info("Admin salvos -" + admin.getCpf());
            }
            adminService.criaAdminLote(leitrua);

        } catch (IOException e) {
            meterRegistry.counter("admin_cadastro_lote", "cadastro_admin", "falha").increment();
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