package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Cargo;
import br.com.sisbrava.repository.CargoRepository;

@ManagedBean(name = "cargos")
public class Cargos {
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getCadastroCargos() {

		CargoRepository cRepository = new CargoRepository();
		Cargo cargo = new Cargo();
		
		cargo.setDescricao(getDescricao());

		cRepository.insert(cargo);
		
		return "cargos.xhtml";

	}
	
	public String cargosCadastroRetorno() {
		return "cargosCadastro.xhtml";
	}

}
