package br.com.cadastro.web.model;

import lombok.Data;

@Data
public class AdminDTO extends UsuarioDTO {

    private CnpjDTO cnpj;

    private String cargo;
}
