package br.edu.iftm.api.easysales.repositories;

import br.edu.iftm.api.easysales.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
}
