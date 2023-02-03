package br.com.cadastro.persistencia.entidade;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@Table(name = "endereco")
public class EnderecoEntidade {
    private String rua;

    private String numero_endereco;

    private String bairro;

    private String cidade;

    private CepEntidade cep;
}
