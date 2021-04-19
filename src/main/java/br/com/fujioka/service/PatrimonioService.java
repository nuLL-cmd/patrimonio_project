package br.com.fujioka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fujioka.entity.Patrimonio;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.PatrimonioRepo;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioRepo repository;

	public ResponseEntity<?> buscaTodos() {
		List<Patrimonio> patrimonios = repository.findAll();
		patrimonios.forEach(p ->{
			p.setProduto(null);
		});
		return ResponseEntity.ok(patrimonios);
	}

	public ResponseEntity<?> buscaPorId(Integer id) {
		if (id > 0) {
			Patrimonio patrimonio = repository.findById(id).orElse(null);
			if (patrimonio != null) {
				
				patrimonio.setProduto(null);
			}
			return patrimonio != null ? ResponseEntity.ok(repository.findById(id)) : ResponseEntity.noContent().build();
		}

		throw new NegocioException("Deve ser informado um id valid para a operação", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> adicionaPatrimonio(Patrimonio patrimonio) {
		if (patrimonio != null) {

			return ResponseEntity.ok(repository.save(patrimonio));

		} else

			throw new NegocioException("Objeto grupo não pode ser nulo.", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<?> atualizaPatrimonio(Patrimonio patrimonio) {
		if (patrimonio.getCodigoPatrimonio() != null && patrimonio.getCodigoPatrimonio() > 0) {
			if (repository.existsById(patrimonio.getCodigoPatrimonio())) {
				return ResponseEntity.ok(repository.save(patrimonio));

			} else

				throw new NegocioException("Patrimonio não encontrado na base de dados.", HttpStatus.NOT_FOUND);

		} else

			throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<?> deletaPatrimonio(Integer id) {
		if (id > 0) {
			
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);
	}

	
	public ResponseEntity<?> buscaPorNome(String nome) {
		List<Patrimonio> subgrupos = repository.findByNomePatrimonioContaining(nome.trim().toUpperCase());
		subgrupos.forEach(s -> {
			s.setFuncionario(null);
			s.setProduto(null);
		});
		return ResponseEntity.ok(subgrupos);
	}

	public ResponseEntity<?> buscaPorMatricula(Integer matricula) {
		if (matricula > 0) {
			List<Patrimonio> subgrupos = repository.findByFuncionario_matricula(matricula);
			subgrupos.forEach(s -> {
				s.setFuncionario(null);
				s.setProduto(null);
			});

			return ResponseEntity.ok(subgrupos);
		}

		throw new NegocioException("Deve ser informado um id válido para a operação.", HttpStatus.BAD_REQUEST);
	}
}
