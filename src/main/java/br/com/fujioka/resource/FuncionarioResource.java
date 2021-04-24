package br.com.fujioka.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fujioka.dto.FuncionarioSimplesDTO;
import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioResource {

	@Autowired
	private FuncionarioService service;

	
	@GetMapping
	@ApiOperation(value = "Recurso responsavel por trazer todos os funcionarios")
	public ResponseEntity<?> buscarTodos() {
		return service.buscarTodos();
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Recurso responsavel por tarzer um funcionario pela sua matricula")
	public ResponseEntity<?> buscaPorId(@Valid @PathVariable Integer id) {
		return service.buscaPorId(id);
	}

	@GetMapping("{nome}/nome")
	@ApiOperation(value = "Recurso que retorna uma lista de funcionarios pelo nome informado")
	public ResponseEntity<?> buscaPorNome(@PathVariable("nome") String nome) {
		return service.buscaPorNome(nome);
	}


	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Recurso responsavel por adicionar um funcionario")
	public ResponseEntity<?> adicionaPessoa(@Valid @RequestBody Funcionario funcionario) {
		return service.adicionaFuncionario(funcionario);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	@ApiOperation(value = "Recurso responsavel por atualizar um funcionario")
	public ResponseEntity<?> atualizaPessoa(@Valid @RequestBody FuncionarioSimplesDTO dto) {
		return service.atualizaFuncionario(dto);
	}
}
