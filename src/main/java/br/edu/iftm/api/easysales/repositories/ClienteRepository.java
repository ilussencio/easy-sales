package br.edu.iftm.api.easysales.repositories;

import br.edu.iftm.api.easysales.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
