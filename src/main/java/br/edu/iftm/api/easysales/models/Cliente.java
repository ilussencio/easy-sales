package br.edu.iftm.api.easysales.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    private String nome;
    private String telefone;
    private String cpfCnpj;
    private String email;

    //RELACIONAMENTO DE CLIENTE E ENDERECO (N:1)
    @ManyToOne
    private Endereco endereco;

    //RELACIONAMENTO ENTRE CLIENTE E VENDEDOR (N:1)
    @ManyToOne
    private Vendedor vendedor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", orphanRemoval = true)
    private List<PedidoVenda> pedidoVendas;
}
