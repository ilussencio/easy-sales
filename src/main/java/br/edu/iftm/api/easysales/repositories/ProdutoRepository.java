package br.edu.iftm.api.easysales.repositories;

import br.edu.iftm.api.easysales.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
