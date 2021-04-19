package br.com.fujioka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fujioka.entity.Funcionario;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Integer> {
		
	List<Funcionario> findBynomeFuncionarioContaining(String nome);
}
