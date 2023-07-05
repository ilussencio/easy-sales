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

public class VendedorDTO extends RepresentationModel<VendedorDTO> implements Serializable {
    private Long idVendedor;
    private String nome;
    private String telefone;
    private String email;
    private String usuario;
    private String senha;
    //private List<ClienteDTO> clienteDTOS;
    //private List<PedidoVendaDTO> pedidoVendaDTOS;
}
