package br.edu.iftm.api.easysales.repositories;

import br.edu.iftm.api.easysales.models.PedidoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long> {
}
