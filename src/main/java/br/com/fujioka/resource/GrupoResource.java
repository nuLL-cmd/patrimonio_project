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

import br.com.fujioka.dto.GrupoDTO;
import br.com.fujioka.service.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("grupos")
@Api(description = "Controller dedicado ao manuseio dos grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoService service;

	@GetMapping
	@ApiOperation(value = "Recurso responsavel por trazer todos os grupo")
	public ResponseEntity<?> buscaTodos() {
		return service.buscarTodos();
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por tarzer um grupo pela sua matricula")
	public ResponseEntity<?> buscaPorId(@Valid @PathVariable Integer id) {
		return service.buscaPorId(id);
	}

	@GetMapping("{nome}/nome")
	@ApiOperation(value = "Recurso que retorna uma lista de grupo pelo nome informado")
	public ResponseEntity<?> buscaPorNome(@PathVariable("nome") String nome) {
		return service.buscaPorNome(nome);
	}
	
	@GetMapping("{id}/funcionario")
	@ApiOperation(value = "Recurso responsavel por listar os grupos cadastrados por um funcioanrio")
	public ResponseEntity<?> buscaPorMatricula(@PathVariable("id") Integer matricula){
		return service.buscaPorMatricula(matricula);
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Recurso responsavel por adicionar um grupo")
	public ResponseEntity<?> adicionaGrupo(@Valid @RequestBody GrupoDTO grupo) {
		return service.adicionaGrupo(grupo);
	}

	@PutMapping
	@ApiOperation(value = "Recurso responsavel por atualizar um grupo")
	public ResponseEntity<?> atualizaGrupo(@Valid @RequestBody GrupoDTO Grupo) {
		return service.atualizaGrupo(Grupo);
	}

}
