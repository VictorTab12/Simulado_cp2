package br.com.fiap.simuladospringpjunidades.repository;

import br.com.fiap.simuladospringpjunidades.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameIgnoreCase(String name);
}
