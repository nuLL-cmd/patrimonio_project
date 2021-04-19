package br.com.fujioka.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.fujioka.builder.GrupoDTOBuilder;
import br.com.fujioka.dto.GrupoDTO;
import br.com.fujioka.dto.GrupoSimplesDTO;
import br.com.fujioka.entity.Grupo;

public class GrupoAdapter extends GrupoDTO {

	private Grupo grupo;
	private List<Grupo> grupos;

	public GrupoAdapter(Grupo grupo) {
		this.grupo = grupo;
	}

	public GrupoAdapter(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public GrupoAdapter() {
	}

	public GrupoDTO converterDTO() {
		if (this.grupo != null) {
			setCodigoGrupo(this.grupo.getCodigoGrupo());
			setDataCriacao(this.grupo.getDataAlteracao());
			setFuncionario(new FuncionarioAdapter(this.grupo.getFuncionario()).converterSimplesDTO());
			setNomeGrupo(this.grupo.getNomeGrupo());

			return this;
		}

		return null;
	}

	public List<GrupoDTO> converterDTOList() {
		List<GrupoDTO> listaGrupoDTO = new ArrayList<>();
		GrupoDTOBuilder grupoBuilder = new GrupoDTOBuilder();

		if (this.grupos != null) {
			this.grupos.forEach(g -> {
				listaGrupoDTO.add(grupoBuilder.codigoGrupo(g.getCodigoGrupo()).dataCriacao(g.getDataAlteracao())
						.nomeGrupo(g.getNomeGrupo())
						.funcionario(new FuncionarioAdapter(g.getFuncionario()).converterSimplesDTO()).build());
			});

			return listaGrupoDTO;
		}
		return new ArrayList<>();
	}
	
	
	
	public Grupo converterGrupo(GrupoDTO dto) {
		if (this.grupo == null) {
			this.grupo = new Grupo();
		}
		
		this.grupo.setCodigoGrupo(dto.getCodigoGrupo());
		this.grupo.setDataAlteracao(dto.getDataCriacao());
		this.grupo.setNomeGrupo(dto.getNomeGrupo());
		this.grupo.setFuncionario(new FuncionarioAdapter().convertFuncionario(dto.getFuncionario()));
		
		return this.grupo;
	}
	
	
	public Grupo converterGrupo(GrupoSimplesDTO grupo) {
		if (this.grupo == null) {
			this.grupo = new Grupo();
		}
		this.grupo.setCodigoGrupo(grupo.getCodigoGrupo());
		this.grupo.setDataAlteracao(grupo.getDataCriacao());
		this.grupo.setNomeGrupo(grupo.getNomeGrupo());
		return this.grupo;
	}
	

	public GrupoSimplesDTO converterSimplesDTO() {
		GrupoSimplesDTO dto = new GrupoSimplesDTO();
		if (this.grupo != null) {
			dto.setCodigoGrupo(this.grupo.getCodigoGrupo());
			dto.setDataCriacao(this.grupo.getDataAlteracao());
			dto.setNomeGrupo(this.grupo.getNomeGrupo());

			return dto;
		}

		return null;
	}

	

}
