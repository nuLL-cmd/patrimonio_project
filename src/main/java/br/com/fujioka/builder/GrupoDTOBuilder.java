package br.com.fujioka.builder;

import java.sql.Date;

import br.com.fujioka.dto.FuncionarioDTO;
import br.com.fujioka.dto.FuncionarioSimplesDTO;
import br.com.fujioka.dto.GrupoDTO;
import br.com.fujioka.entity.Endereco;
import br.com.fujioka.entity.Grupo;
import br.com.fujioka.enumerator.SituacaoEnum;
import javassist.compiler.ast.NewExpr;

public class GrupoDTOBuilder {

	private Integer codigoGrupo;
	private String nomeGrupo;
	private Date dataCriacao;

	private FuncionarioSimplesDTO funcionario;;

	public GrupoDTO build() {
		return new GrupoDTO(this.codigoGrupo, this.nomeGrupo, this.dataCriacao, this.funcionario);
	}

	public GrupoDTOBuilder codigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
		return this;
	}

	public GrupoDTOBuilder nomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
		return this;
	}

	public GrupoDTOBuilder dataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}

	public GrupoDTOBuilder funcionario(FuncionarioSimplesDTO funcionario) {
		this.funcionario = funcionario;
		return this;
	}

}
