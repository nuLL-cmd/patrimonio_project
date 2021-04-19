package br.com.fujioka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.Grupo;

@Repository
public interface GrupoRepo extends JpaRepository<Grupo, Integer>{
	
	List<Grupo> findByNomeGrupoContaining(String nome);
	List<Grupo> findByFuncionario_matricula(Integer matricula);

}
