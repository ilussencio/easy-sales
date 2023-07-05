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

public class CategoriaDTO  extends RepresentationModel<CategoriaDTO> implements Serializable {
    private Long idCategoria;
    private String nome;

    //private List<ProdutoDTO> produtoDTOS;
}
