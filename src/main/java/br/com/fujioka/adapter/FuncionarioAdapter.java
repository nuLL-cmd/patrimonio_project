package br.com.fujioka.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.fujioka.builder.FuncionarioDTOBuilder;
import br.com.fujioka.dto.FuncionarioDTO;
import br.com.fujioka.dto.FuncionarioSimplesDTO;
import br.com.fujioka.entity.Funcionario;

public class FuncionarioAdapter extends FuncionarioDTO {

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;

	public FuncionarioAdapter(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioAdapter(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioAdapter() {

	}

	public FuncionarioDTO converterDTO() {

		if (this.funcionario != null) {
			setEndereco(this.funcionario.getEndereco());
			setContato(this.funcionario.getContato());
			setMatricula(this.funcionario.getMatricula());
			setUsuario(this.funcionario.getUsuario());
			setNome(this.funcionario.getNomeFuncionario());
			setSituacao(this.funcionario.getSituacao());

			return this;
		}

		return null;
	}

	public List<FuncionarioDTO> converterListDTO() {
		List<FuncionarioDTO> funcionarioSaidas = new ArrayList<>();
		if (funcionarios != null) {
			funcionarios.forEach(f -> {
				funcionarioSaidas.add(new FuncionarioDTOBuilder().matricula(f.getMatricula())
						.nome(f.getNomeFuncionario()).situacao(f.getSituacao()).usuario(f.getUsuario())
						.endereco(f.getEndereco()).contato(f.getContato()).build());
			});

			return funcionarioSaidas;
		}

		return new ArrayList<>();
	}

	public Funcionario convertFuncionario (FuncionarioSimplesDTO dto) {
			if (this.funcionario == null) {
				this.funcionario = new Funcionario();
			}
			
			this.funcionario.setNomeFuncionario(dto.getNome());
			this.funcionario.setSituacao(dto.getSituacao());
			this.funcionario.setUsuario(dto.getUsuario());
			this.funcionario.setMatricula(dto.getMatricula());
			
			return this.funcionario;
	

	}

	public FuncionarioSimplesDTO converterSimplesDTO() {
		FuncionarioSimplesDTO dto = new FuncionarioSimplesDTO();
		if (this.funcionario != null) {
			dto.setMatricula(this.funcionario.getMatricula());
			dto.setNome(this.funcionario.getNomeFuncionario());
			dto.setSituacao(this.funcionario.getSituacao());
			dto.setUsuario(this.funcionario.getUsuario());

			return dto;
		}

		return null;
	}

}
