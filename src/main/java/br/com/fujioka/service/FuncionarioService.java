package br.com.fujioka.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujioka.adapter.FuncionarioAdapter;
import br.com.fujioka.dto.FuncionarioDTO;
import br.com.fujioka.dto.FuncionarioSimplesDTO;
import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.exception.entity.CamposErroHandler;
import br.com.fujioka.exception.entity.ErroHandler;
import br.com.fujioka.repository.FuncionarioRepo;
import br.com.fujioka.utils.Utilitarios;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepo repository;

	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(new FuncionarioAdapter(repository.findAll()).converterListDTO());
	}

	public ResponseEntity<?> buscaPorId(Integer id) {
		if (id > 0) {
			Funcionario funcionario = repository.findById(id).orElseThrow(
					() -> new NegocioException("Busca não retornou nenhum resultado", HttpStatus.NOT_FOUND));

			FuncionarioDTO dto = new FuncionarioAdapter(funcionario).converterDTO();
			return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();
		}
		throw new NegocioException("Necessario um id valido para essa operação", HttpStatus.BAD_REQUEST);
	}
	

	public ResponseEntity<?> adicionaFuncionario(Funcionario funcionario){

		if(funcionario.getContato() == null)
			return validaEntidadeFuncionario("contato");
			
		FuncionarioDTO dto = new FuncionarioAdapter(repository.save(funcionario)).converterDTO();
		return ResponseEntity.ok(dto);

	}

	private ResponseEntity<?> validaEntidadeFuncionario(String campoNome) {
		ErroHandler erro = new ErroHandler();
		List<CamposErroHandler> campos = new ArrayList<>();
		campos.add(new CamposErroHandler(campoNome,"Precisa ser informado um "+campoNome));
		erro.setCamposErro(campos);
		erro.setData(Utilitarios.formateDateTime(OffsetDateTime.now().toEpochSecond()));
		erro.setStatus(400);
		erro.setMessage("Um ou mais campos são invalidos.");
		
		return ResponseEntity.badRequest().body(erro);
	}

	public ResponseEntity<?> atualizaFuncionario(FuncionarioSimplesDTO dto) {
		
		if (dto.getMatricula() != null && dto.getMatricula() > 0) {
			Funcionario funcionario = repository.findById(dto.getMatricula()).orElseThrow(
					() -> new NegocioException("Funcionario não encontrado na base de dados", HttpStatus.NOT_FOUND));
			
			funcionario = new FuncionarioAdapter(funcionario).convertFuncionario(dto);
			return ResponseEntity.ok(new FuncionarioAdapter(repository.save(funcionario)).converterDTO());

		} else

			throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<?> buscaPorNome(String nome) {
		return ResponseEntity.ok(new FuncionarioAdapter(repository.findBynomeFuncionarioContaining(nome.toUpperCase()))
				.converterListDTO());
	}

}
