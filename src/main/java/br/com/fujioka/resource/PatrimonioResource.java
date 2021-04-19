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

import br.com.fujioka.entity.Patrimonio;
import br.com.fujioka.service.PatrimonioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("patrimonios")
public class PatrimonioResource {

	@Autowired
	private PatrimonioService service;
	

	@GetMapping
	@ApiOperation(value = "Recurso responsavel por trazer todos os patrimonios")
	public ResponseEntity<?> buscaTodos() {
		return service.buscaTodos();
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por tarzer um patrimonio pelo seu codigo")
	public ResponseEntity<?> buscaPorId(@Valid @PathVariable Integer id) {
		return service.buscaPorId(id);
	}

	@GetMapping("{nome}/nome")
	@ApiOperation(value = "Recurso que retorna uma lista de patrimonios o pelo nome informado")
	public ResponseEntity<?> buscaPorNome(@PathVariable("nome") String nome) {
		return service.buscaPorNome(nome);
	}
	
	@GetMapping("{id}/funcionario")
	@ApiOperation(value = "Recurso responsavel por listar os patrimonios cadastrados por um funcionario")
	public ResponseEntity<?> buscaPorMatricula(@PathVariable("id") Integer matricula){
		return service.buscaPorMatricula(matricula);
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Recurso responsavel por adicionar um novo patrimonio")
	public ResponseEntity<?> adicionaPessoa(@Valid @RequestBody Patrimonio patrimonio) {
		return service.adicionaPatrimonio(patrimonio);
	}

	@PutMapping
	@ApiOperation(value = "Recurso responsavel por atualizar um patrimonio")
	public ResponseEntity<?> atualizaPessoa(@Valid @RequestBody Patrimonio patrimonio) {
		return service.atualizaPatrimonio(patrimonio);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por deletar um patrimonio por codigo")
	public ResponseEntity<?> deletaPatrimonio(@PathVariable("id") Integer id) {
		return service.deletaPatrimonio(id);
	}
	
	
}
