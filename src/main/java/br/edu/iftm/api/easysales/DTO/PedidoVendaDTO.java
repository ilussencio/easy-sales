package br.edu.iftm.api.easysales.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PedidoVendaDTO extends RepresentationModel<PedidoVendaDTO>  implements Serializable {

    private Long idPedido;
    private float desconto;
    private float valorTotal;
    private LocalDate data;
    private String status;

    private List<ProdutoDTO> produtos;
    private VendaDTO venda;
    private FormaPagamentoDTO formaPagamento;

    private ClienteDTO cliente;
}
