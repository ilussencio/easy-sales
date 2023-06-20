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

public class ProdutoDTO extends RepresentationModel<ProdutoDTO>  implements Serializable {
    private Long idProduto;
    private String nome;
    private String images;
    private String estoque;
    private String descricao;
    private String preco;
    private CategoriaDTO categoriaDTO;
    private List<PedidoVendaDTO> pedidoVendaDTOS;
}
