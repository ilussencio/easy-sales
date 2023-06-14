package br.edu.iftm.api.easysales.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormaPagamento;
    private String descricao;
    private String nome;

    //RELACIONAMENTO ENTRE FORMA PAGAMENTO E PEDIDO VENDA (1:N)
    @OneToMany
    private List<PedidoVenda> pedidoVendas;
}
