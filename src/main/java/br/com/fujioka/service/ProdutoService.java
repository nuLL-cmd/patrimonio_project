package br.com.fujioka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujioka.adapter.ProdutoAdapter;
import br.com.fujioka.dto.ProdutoDTO;
import br.com.fujioka.dto.ProdutoInputDTO;
import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.entity.Grupo;
import br.com.fujioka.entity.Produto;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.FuncionarioRepo;
import br.com.fujioka.repository.GrupoRepo;
import br.com.fujioka.repository.ProdutoRepo;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepo repository;
	
	@Autowired
	private FuncionarioRepo funcionarioRepo;
	
	@Autowired 
	private GrupoRepo grupoRepo;
	

	public ResponseEntity<?> buscaTodos() {
		List<Produto> produtos = repository.findAll();	
		return ResponseEntity.ok(new ProdutoAdapter(produtos).converterListDTO());
	}

	public ResponseEntity<?> buscaPorId(Integer id) {
		if (id > 0) {
			Produto produto = repository.findById(id).orElse(null);
			ProdutoDTO dto = new ProdutoAdapter(produto).converterDTO();
			return produto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();
		}

		throw new NegocioException("Deve ser informado um id valid para a operação", HttpStatus.BAD_REQUEST);
	}
	

	
	
	public ResponseEntity<?> adicionaProduto(ProdutoInputDTO dto) {
		Funcionario funcionario = funcionarioRepo.findById(dto.getFuncionario().getMatricula()).orElseThrow(()->
				new NegocioException("Funcionario não encontrado na base de dados",HttpStatus.BAD_REQUEST));
		
		Grupo grupo = grupoRepo.findById(dto.getGrupo().getCodigoGrupo()).orElseThrow(()->
			new NegocioException("Grupo não encontrado na base de addos", HttpStatus.BAD_REQUEST));
		return null;
		
	}

	public ResponseEntity<?> atualizaProduto(Produto produto) {
		if (produto.getCodigoProduto() != null && produto.getCodigoProduto() > 0) {
			if (repository.existsById(produto.getCodigoProduto())) {
				return ResponseEntity.ok(repository.save(produto));

			} else

				throw new NegocioException("Produto não encontrado na base de dados.", HttpStatus.NOT_FOUND);

		} else

			throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<?> deletaProduto(Integer id) {
		if (id > 0) {
			
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> buscaPorNome(String nome) {
		List<Produto> produtos = repository.findByNomeProdutoContaining(nome.trim().toUpperCase());
		produtos.forEach(s -> {
			s.setFuncionario(null);
		});
		return ResponseEntity.ok(repository.findByNomeProdutoContaining(nome.toUpperCase()));
	}

	public ResponseEntity<?> buscaPorMatricula(Integer matricula) {
		if (matricula > 0) {
			List<Produto> subgrupos = repository.findByFuncionario_matricula(matricula);
			subgrupos.forEach(s -> {
				s.setFuncionario(null);
			});

			return ResponseEntity.ok(subgrupos);
		}

		throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);
	}


}
