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

public class FormaPagamentoDTO extends RepresentationModel<FormaPagamentoDTO> implements Serializable {
    private Long idFormaPagamento;
    private String descricao;
    private String nome;

    private List<PedidoVendaDTO> pedidoVendaDTOS;
}
