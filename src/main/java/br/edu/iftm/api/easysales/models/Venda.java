package br.edu.iftm.api.easysales.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    //RELACIONAMENTO ENTRE VENDA E PEDIDO VENDA
    @OneToOne
    private PedidoVenda pedidoVenda;
}
