package br.com.fujioka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujioka.adapter.FuncionarioAdapter;
import br.com.fujioka.adapter.GrupoAdapter;
import br.com.fujioka.dto.GrupoDTO;
import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.entity.Grupo;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.FuncionarioRepo;
import br.com.fujioka.repository.GrupoRepo;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepo repository;

	@Autowired
	private FuncionarioRepo funcionarioRepo;
	

	public ResponseEntity<?> buscarTodos() {
		List<Grupo> grupos = repository.findAll();
		return ResponseEntity.ok(new GrupoAdapter(grupos).converterDTOList());
	}

	public ResponseEntity<?> buscaPorId(Integer id) {
		if (id > 0) {
			GrupoDTO dto = new GrupoAdapter(repository.findById(id).orElse(null)).converterDTO();
			return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();
		}
		throw new NegocioException("Necessario um id valid para essa operação", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> adicionaGrupo(GrupoDTO dto) {
		Funcionario funcionario = funcionarioRepo.findById(dto.getFuncionario().getMatricula()).orElse(null);
		
		dto.setFuncionario(new FuncionarioAdapter(funcionario).converterSimplesDTO());
		
		Grupo grupo = new GrupoAdapter().converterGrupo(dto);
		grupo = repository.save(grupo);
			return ResponseEntity.ok(new GrupoAdapter(grupo).converterDTO());

	}

	
	public ResponseEntity<?> atualizaGrupo(GrupoDTO dto) {
		if (dto.getCodigoGrupo() != null && dto.getCodigoGrupo() > 0) {
				Funcionario funcionario = funcionarioRepo.findById(dto.getFuncionario().getMatricula()).orElseThrow(() ->
					 new NegocioException("Grupo não encontrado na base de dados.", HttpStatus.NOT_FOUND));
				
				dto.setFuncionario(new FuncionarioAdapter(funcionario).converterSimplesDTO());
				Grupo grupo = new GrupoAdapter().converterGrupo(dto);
				grupo = repository.save(grupo);
				return ResponseEntity.ok(new GrupoAdapter(grupo).converterDTO());



		} else

			throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);

	}

	
	public ResponseEntity<?> buscaPorNome(String nome) {
		List<Grupo> grupos = repository.findByNomeGrupoContaining(nome.toUpperCase());
		return ResponseEntity.ok(new GrupoAdapter(grupos).converterDTOList());
	}

	
	
	public ResponseEntity<?> buscaPorMatricula(Integer matricula) {
		if (matricula > 0) {
			List<Grupo> grupos = repository.findByFuncionario_matricula(matricula);
			return ResponseEntity.ok(new GrupoAdapter(grupos).converterDTOList());
		}

		throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);
	}
	
}
