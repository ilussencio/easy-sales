package br.edu.iftm.api.easysales.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EnderecoDTO extends RepresentationModel<EnderecoDTO> implements Serializable {
    private Long idEndereco;
    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;

    private ClienteDTO clienteDTO;
}
