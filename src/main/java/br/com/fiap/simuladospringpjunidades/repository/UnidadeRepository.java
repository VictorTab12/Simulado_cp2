package br.com.fiap.simuladospringpjunidades.repository;

import br.com.fiap.simuladospringpjunidades.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
