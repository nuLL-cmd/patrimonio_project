package br.com.fujioka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.SubGrupo;

@Repository
public interface SubGrupoRepo extends JpaRepository<SubGrupo, Integer>{

	List<SubGrupo> findByNomeSubgrupoContaining(String upperCase);
	List<SubGrupo> findByFuncionario_matricula(Integer matricula);

}
