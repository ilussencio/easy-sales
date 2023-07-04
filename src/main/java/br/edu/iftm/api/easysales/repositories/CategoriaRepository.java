package br.edu.iftm.api.easysales.repositories;

import br.edu.iftm.api.easysales.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
