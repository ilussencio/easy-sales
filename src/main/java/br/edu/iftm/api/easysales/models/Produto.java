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
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private String images;
    private int estoque;
    private String descricao;
    private float preco;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    //RELACIONAMENTO PRODUTO E PEDIDO VENDA (N:N)
    @ManyToMany(mappedBy = "produtos")
    private List<PedidoVenda> pedidoVendas;

}
