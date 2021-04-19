package br.com.fujioka.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujioka.adapter.FuncionarioAdapter;
import br.com.fujioka.adapter.GrupoAdapter;
import br.com.fujioka.adapter.SubgrupoAdapter;
import br.com.fujioka.dto.SubgrupoDTO;
import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.entity.Grupo;
import br.com.fujioka.entity.SubGrupo;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.FuncionarioRepo;
import br.com.fujioka.repository.GrupoRepo;
import br.com.fujioka.repository.SubGrupoRepo;

@Service
public class SubGrupoService {

	@Autowired
	private SubGrupoRepo repository;

	@Autowired
	private FuncionarioRepo funcionarioRepo;

	@Autowired
	private GrupoRepo grupoRepo;

	public ResponseEntity<?> buscaTodos() {
		List<SubgrupoDTO> subGrupos = new SubgrupoAdapter(repository.findAll()).converterDTOList();
		return ResponseEntity.ok(subGrupos);
	}

	public ResponseEntity<?> buscaPorId(Integer id) {
		if (id > 0) {
			SubGrupo subGrupo = repository.findById(id).orElse(null);
			SubgrupoDTO dto = new SubgrupoAdapter(subGrupo).converterDTO();

			return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.noContent().build();
		}

		throw new NegocioException("Deve ser informado um id valid para a operação", HttpStatus.BAD_REQUEST);
	}

	public SubgrupoDTO adicionaSubGrupo(SubgrupoDTO subGrupo) {

		Funcionario funcionario = funcionarioRepo.findById(subGrupo.getFuncionario().getMatricula()).orElseThrow(
				() -> new NegocioException("Funcionario inforamdo não encontrado na base de dados", HttpStatus.BAD_REQUEST));

		Grupo grupo = grupoRepo.findById(subGrupo.getGrupo().getCodigoGrupo())
				.orElseThrow(() -> new NegocioException("Grupo informado não encontrado na base de dados", HttpStatus.BAD_REQUEST));

		subGrupo.setFuncionario(new FuncionarioAdapter(funcionario).converterSimplesDTO());
		subGrupo.setGrupo(new GrupoAdapter(grupo).converterSimplesDTO());
		return new SubgrupoAdapter(repository.save(new SubgrupoAdapter().converterSubggrupo(subGrupo))).converterDTO();

	}

	public ResponseEntity<?> atualizaSubGrupo(SubgrupoDTO subGrupo) {
		if (subGrupo.getCodigo() != null && subGrupo.getCodigo() > 0) {
			if (repository.existsById(subGrupo.getCodigo())) {
				Funcionario funcionario = funcionarioRepo.findById(subGrupo.getFuncionario().getMatricula()).orElseThrow(
						() -> new NegocioException("Funcionario inforamdo não encontrado na base de dados", HttpStatus.BAD_REQUEST));

				Grupo grupo = grupoRepo.findById(subGrupo.getGrupo().getCodigoGrupo())
						.orElseThrow(() -> new NegocioException("Grupo informado não encontrado na base de dados", HttpStatus.BAD_REQUEST));
				
				subGrupo.setFuncionario(new FuncionarioAdapter(funcionario).converterSimplesDTO());
				subGrupo.setGrupo(new GrupoAdapter(grupo).converterSimplesDTO());
				return  ResponseEntity.ok(new SubgrupoAdapter(repository.save(new SubgrupoAdapter().converterSubggrupo(subGrupo))).converterDTO());

			} else

				throw new NegocioException("SubGrupo não encontrado na base de dados.", HttpStatus.NOT_FOUND);

		} else

			throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<?> buscaPorNome(String nome) {
		List<SubgrupoDTO> subgrupos = new SubgrupoAdapter(repository.findByNomeSubgrupoContaining(nome.toUpperCase()))
				.converterDTOList();
		return ResponseEntity.ok(subgrupos);
	}

	public ResponseEntity<?> buscaPorMatricula(Integer matricula) {
		if (matricula > 0) {
			List<SubgrupoDTO> grupos = new SubgrupoAdapter(repository.findByFuncionario_matricula(matricula))
					.converterDTOList();
			return ResponseEntity.ok(grupos);
		}

		throw new NegocioException("Deve ser informado uma matricula válida para a operação.", HttpStatus.BAD_REQUEST);
	}

}
