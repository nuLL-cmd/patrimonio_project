package br.com.fujioka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fujioka.entity.Perfil;

public interface PerfilRepo extends JpaRepository<Perfil, Integer> {
    
    
}
