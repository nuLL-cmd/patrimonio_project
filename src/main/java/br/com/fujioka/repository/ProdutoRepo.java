package br.com.fujioka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.Produto;

@Repository
public interface ProdutoRepo extends JpaRepository<Produto, Integer>{

	List<Produto> findByNomeProdutoContaining(String nome);
	List<Produto> findByFuncionario_matricula(Integer matricula);

}
