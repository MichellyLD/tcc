package br.com.michelly.tcc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.michelly.tcc.model.Conta;

@Repository
public interface UsuarioRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findByEmail(String email);
}
