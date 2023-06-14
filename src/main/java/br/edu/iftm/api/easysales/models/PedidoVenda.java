package br.edu.iftm.api.easysales.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class PedidoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private float desconto;
    private float valorTotal;
    private LocalDate data;
    private String status;
    //RELACIONAMENTO ENTRE PEDIDO VENDA E PRODUTO (N:N)
    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idProduto"))
    private List<Produto> produtos;

    //RELACIONAMENTO ENTRE PEDIDO VENDA E VENDA (1:1)
    @OneToOne(mappedBy = "pedidoVenda")
    private Venda venda;

    //RELACIONAMENTO ENTRE PEDIDO VENDA E FORMA DE PAGAMENTO (1:1)
    @OneToOne
    @JoinColumn(name = "idFormaPagamento")
    private FormaPagamento formaPagamento;

    //RELACIONAMENTO ENTRE PEDIDO VENDA E ENDERECO (1:1)
    @OneToOne
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

    //RELACIONAMENTO ENTRE PEDIDO VENDA E CLIENTE (1:1)
    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    //RELACIONAMENTO ENTRE PEDIDO VENDA E VENDEDOR (1:1)
    @OneToOne
    @JoinColumn(name = "idVendedor")
    private Vendedor vendedor;
}
