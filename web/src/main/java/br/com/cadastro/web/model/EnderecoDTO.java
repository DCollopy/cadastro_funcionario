package br.com.cadastro.web.model;

import lombok.Data;

@Data
public class EnderecoDTO {

    private String rua;

    private String numero_endereco;

    private String bairro;

    private String cidade;

    private CepDTO cep;
}
