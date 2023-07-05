package br.edu.iftm.api.easysales.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClienteDTO extends RepresentationModel<ClienteDTO>  implements Serializable {
    private Long idCliente;
    private String nome;
    private String telefone;
    private String cpfCnpj;
    private String email;
    private EnderecoDTO endereco;
    private VendedorDTO vendedor;
}
