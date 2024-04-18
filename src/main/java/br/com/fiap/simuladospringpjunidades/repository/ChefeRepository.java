package br.com.fiap.simuladospringpjunidades.repository;

import br.com.fiap.simuladospringpjunidades.entity.Chefe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefeRepository extends JpaRepository<Chefe, Long> {
}
