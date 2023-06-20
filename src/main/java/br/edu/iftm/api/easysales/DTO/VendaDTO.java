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

public class VendaDTO extends RepresentationModel<VendaDTO>  implements Serializable {
    private Long idVenda;
    private PedidoVendaDTO pedidoVendaDTO;
}
