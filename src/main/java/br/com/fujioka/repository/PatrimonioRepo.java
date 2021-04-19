package br.com.fujioka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.Patrimonio;

@Repository
public interface PatrimonioRepo  extends JpaRepository<Patrimonio,Integer>{

	List<Patrimonio> findByNomePatrimonioContaining(String nome);
	List<Patrimonio> findByFuncionario_matricula(Integer matricula);

}
