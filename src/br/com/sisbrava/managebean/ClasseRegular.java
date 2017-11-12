package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Classes;
import br.com.sisbrava.repository.ClassesRepository;

@ManagedBean(name = "classeRegular")
public class ClasseRegular {

	private String descricao;
	private String idadePermitida;

	public String getIdadePermitida() {
		return idadePermitida;
	}

	public void setIdadePermitida(String idadePermitida) {
		this.idadePermitida = idadePermitida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCadastroClasseRegular() {

		ClassesRepository cRepository = new ClassesRepository();
		Classes classe = new Classes();
		
		classe.setDescricao(this.descricao);
		classe.setIdade_permitida(Integer.parseInt(this.idadePermitida));

		cRepository.insert(classe);

		return "classesRegulares.xhtml";

	}

	public String classeRegularCadastroRetorno() {
		return "classesRegularesCadastro.xhtml";
	}

}
