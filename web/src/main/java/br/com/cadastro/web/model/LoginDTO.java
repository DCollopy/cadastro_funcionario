package br.com.cadastro.web.model;


import lombok.Data;

import java.util.List;

@Data
public class LoginDTO {
    private CpfDTO cpf;
    private EmailDTO email;
    private String senha;
    private String cargo;
}
