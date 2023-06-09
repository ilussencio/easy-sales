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
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVendedor;
    private String nome;
    private String telefone;
    private String email;
    private String usuario;
    private String senha;

    //RELACIONAMENTO ENTRE VENDEDOR E CLIENTE (1:N)
    @OneToMany
    private List<Cliente> clientes;

    //RELACIONAMENTO ENTRE VENDEDOR E PEDIDO DE VENDA (1:N)
    @OneToMany
    private List<PedidoVenda> pedidoVendas;
}
