package br.com.cadastro.persistencia.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;


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
