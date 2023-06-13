package br.edu.iftm.api.easysales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Venda extends JpaRepository<Venda, Long>{
}
