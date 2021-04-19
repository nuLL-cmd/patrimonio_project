package br.com.fujioka.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fujioka.dto.SubgrupoDTO;
import br.com.fujioka.service.SubGrupoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("subgrupos")
public class SubGrupoResource {
	
	
	@Autowired
	private SubGrupoService service;
	
	@GetMapping
	@ApiOperation(value = "Recurso responsavel por trazer todos os subgrupos")
	public ResponseEntity<?> buscaTodos() {
		return service.buscaTodos();
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por tarzer um subrupo pelo seu codigo")
	public ResponseEntity<?> buscaPorId(@Valid @PathVariable Integer id) {
		return service.buscaPorId(id);
	}

	@GetMapping("{nome}/nome")
	@ApiOperation(value = "Recurso que retorna uma lista de subgrupos o pelo nome informado")
	public ResponseEntity<?> buscaPorNome(@PathVariable("nome") String nome) {
		return service.buscaPorNome(nome);
	}
	
	@GetMapping("{id}/funcionario")
	@ApiOperation(value = "Recurso responsavel por listar os subgrupos cadastrados por um usuario")
	public ResponseEntity<?> buscaPorMatricula(@PathVariable("id") Integer matricula){
		return service.buscaPorMatricula(matricula);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Recurso responsavel por adicionar um novo subgrupo")
	public SubgrupoDTO adicionaPessoa(@Valid @RequestBody SubgrupoDTO subGrupo) {
		return service.adicionaSubGrupo(subGrupo);
	}

	@PutMapping
	@ApiOperation(value = "Recurso responsavel por atualizar um subgrupo")
	public ResponseEntity<?> atualizaPessoa(@Valid @RequestBody SubgrupoDTO subGrupo) {
		return service.atualizaSubGrupo(subGrupo);
	}

	

}
