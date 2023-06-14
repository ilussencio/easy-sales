package br.edu.iftm.api.easysales.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    //RELACIONAMENTO ENTRE VENDA E PEDIDO VENDA (1:1)
    @OneToOne
    @JoinColumn(name = "idPedidoVenda")
    private PedidoVenda pedidoVenda;
}
