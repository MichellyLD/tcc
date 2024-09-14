package br.com.michelly.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.michelly.tcc.model.Conta;


public interface UsuarioRepository extends JpaRepository<Conta, Long>{
        
}
