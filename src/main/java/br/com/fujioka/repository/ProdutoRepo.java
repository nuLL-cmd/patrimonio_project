package br.com.fujioka.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.Produto;
import br.com.fujioka.enumerator.GeneroEnum;

@Repository
public interface ProdutoRepo extends JpaRepository<Produto, Integer>{

	List<Produto> findByNomeProdutoContaining(String nome);
	List<Produto> findByFuncionario_matricula(Integer matricula);

}
