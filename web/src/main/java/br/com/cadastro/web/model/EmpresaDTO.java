package br.com.cadastro.web.model;

import br.com.cadastro.dominio.entidade.objetos.Cnpj;
import lombok.Data;

@Data
public class EmpresaDTO {
    private String razaosocial;
    private String nomefantasia;
    private Cnpj cnpj;
}
