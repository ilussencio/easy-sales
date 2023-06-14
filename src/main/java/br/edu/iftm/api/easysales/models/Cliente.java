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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nome;
    private String telefone;
    private String cpfCnpj;
    private String email;

    //RELACIONAMENTO DE CLIENTE E ENDERECO (1:N)
    @OneToMany
    private List<Endereco> enderecos;

    //RELACIONAMENTO ENTRE CLIENTE E VENDEDOR (1:1)
    @OneToOne
    @JoinColumn(name = "idVendedor")
    private Vendedor vendedor;
}
