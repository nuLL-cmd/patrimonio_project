package br.com.fujioka.adapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.fujioka.builder.SubgrupoDTOBuilder;
import br.com.fujioka.dto.FuncionarioDTO;
import br.com.fujioka.dto.GrupoDTO;
import br.com.fujioka.dto.SubgrupoDTO;
import br.com.fujioka.entity.Grupo;
import br.com.fujioka.entity.SubGrupo;

public class SubgrupoAdapter extends SubgrupoDTO{


	private SubGrupo subgrupo;
	private List<SubGrupo> subgrupos;
	
	public SubgrupoAdapter(SubGrupo subgrupo) {
		this.subgrupo = subgrupo;
	}
	
	public SubgrupoAdapter() {}
	
	public SubgrupoAdapter(List<SubGrupo> subgrupos) {
		this.subgrupos = subgrupos;
	}
	
	public SubgrupoDTO converterDTO() {
		if (this.subgrupo != null) {
			setCodigo(this.subgrupo.getCodigoSubgrupo());
			setDataCriacao(this.subgrupo.getDataAlteracao());
			setNomeSubgrupo(this.subgrupo.getNomeSubgrupo());
			setFuncionario(new FuncionarioAdapter(this.subgrupo.getFuncionario())
					.converterSimplesDTO());
			setGrupo(new GrupoAdapter(this.subgrupo.getGrupo()).converterSimplesDTO());
			return this;
		}
		
		return null;
	}
	
	public List<SubgrupoDTO> converterDTOList(){
		List<SubgrupoDTO> listaSubgrupos = new ArrayList<>();
		SubgrupoDTOBuilder builder  = new SubgrupoDTOBuilder();
		if (this.subgrupos != null) {
			this.subgrupos.forEach(s ->{
				listaSubgrupos.add(builder.codigo(s.getCodigoSubgrupo())
						.dataCriacao(s.getDataAlteracao())
						.grupo(new GrupoAdapter(s.getGrupo()).converterSimplesDTO())
						.funcionario(new FuncionarioAdapter(s.getFuncionario()).converterSimplesDTO())
						.nomeSubgrupo(s.getNomeSubgrupo()).builder());
			});
			
			return listaSubgrupos;
		}
		
		return new ArrayList<>();
		
	}
	
	
	public SubGrupo converterSubggrupo(SubgrupoDTO dto) {
		if(this.subgrupo == null)
			this.subgrupo = new SubGrupo();
		this.subgrupo.setCodigoSubgrupo(dto.getCodigo());
		this.subgrupo.setNomeSubgrupo(dto.getNomeSubgrupo());
		this.subgrupo.setDataAlteracao(dto.getDataCriacao());
		this.subgrupo.setGrupo(new GrupoAdapter().converterGrupo(dto.getGrupo()));
		this.subgrupo.setFuncionario(new FuncionarioAdapter().convertFuncionario(dto.getFuncionario()));
		
		return this.subgrupo;
	}
	
	

	
}