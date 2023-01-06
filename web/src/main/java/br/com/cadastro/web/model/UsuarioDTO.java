package br.com.cadastro.web.model;

import lombok.Data;

@Data
public class UsuarioDTO {

    private CpfDTO cpf;
    private String nome;

    private String sobrenome;

    private EmailDTO email;

    private TelefoneDTO telefone;

    private EnderecoDTO endereco;

    private String cargo;
}
