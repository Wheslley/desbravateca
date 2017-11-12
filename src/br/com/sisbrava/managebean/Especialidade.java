package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Especialidades;
import br.com.sisbrava.repository.EspecialidadesRepository;

@ManagedBean(name = "especialidade")
public class Especialidade {
	
	private String descricao;
	private String dificuldade;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getCadastroEspecialidade() {

		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidade = new Especialidades();
		
		especialidade.setDescricao(this.descricao);
		especialidade.setDificuldade(this.dificuldade);
		
		eRepository.insert(especialidade);
		
		return "especialidades.xhtml";

	}
	
	public String especialidadeCadastroRetorno() {
		return "especialidadesCadastro.xhtml";
	}

}
