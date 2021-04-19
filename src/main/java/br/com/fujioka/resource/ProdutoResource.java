package br.com.fujioka.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fujioka.dto.ProdutoDTO;
import br.com.fujioka.dto.ProdutoInputDTO;
import br.com.fujioka.entity.Produto;
import br.com.fujioka.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("produtos")
@Api(description = "Descrição teste")
public class ProdutoResource {
	

	@Autowired
	private ProdutoService service;
	
	
	@GetMapping
	@ApiOperation(value = "Recurso responsavel por trazer todos os produtos")
	public ResponseEntity<?> buscaTodos() {
		return service.buscaTodos();
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por tarzer um produto pelo seu codigo")
	public ResponseEntity<?> buscaPorId(@Valid @PathVariable Integer id) {
		return service.buscaPorId(id);
	}

	@GetMapping("{nome}/nome")
	@ApiOperation(value = "Recurso que retorna uma lista de produtos o pelo nome informado")
	public ResponseEntity<?> buscaPorNome(@PathVariable("nome") String nome) {
		return service.buscaPorNome(nome);
	}
	
	@GetMapping("{id}/funcionario")
	@ApiOperation(value = "Recurso responsavel por listar os produtos cadastrados por um funcionario")
	public ResponseEntity<?> buscaPorMatricula(@PathVariable("id") Integer matricula){
		return service.buscaPorMatricula(matricula);
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Recurso responsavel por adicionar um novo produto")
	public ResponseEntity<?> adicionaPessoa(@Valid @RequestBody ProdutoInputDTO produto) {
		return service.adicionaProduto(produto);
	}

	@PutMapping
	@ApiOperation(value = "Recurso responsavel por atualizar um produto")
	public ResponseEntity<?> atualizaPessoa(@Valid @RequestBody Produto produto) {
		return service.atualizaProduto(produto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por deletar um produto por codigo")
	public ResponseEntity<?> deletaProduto(@PathVariable("id") Integer id) {
		return service.deletaProduto(id);
	}
	
	
	
}
