package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Cargo;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.CargoRepository;

@ManagedBean(name = "cargos")
public class Cargos {
	
	private String id;
	private String descricao;
	private List<Cargo> listaCargos;
	private List<Cargo> listaCargosFiltrados;
	
	public List<Cargo> getListaCargos(){
		
		listaCargos = new ArrayList<Cargo>();
		CargoRepository cRepository = new CargoRepository();
		
		List<Object> lista = cRepository.selectMultiplusObjects(ITables.CARGO);
		
		for(Object o : lista) {
			Cargo cargo = new Cargo();
			cargo = (Cargo) o;
			listaCargos.add(cargo);
		}
		
		return listaCargos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public String buscarID() {
		
		Integer id;
		id = Integer.parseInt(this.getId());
		CargoRepository cRepository = new CargoRepository();
		Cargo cargo = (Cargo) cRepository.getSelectOneObject(id);
		
		this.setDescricao(cargo.getDescricao());
		
		return "";
	}
	
	public String getAlterarCargos() {

		CargoRepository cRepository = new CargoRepository();
		Cargo cargo = (Cargo) cRepository.getSelectOneObject(Integer.parseInt(this.getId()));
		cargo.setDescricao(this.getDescricao());
		
		cRepository.update(cargo);
		
		return "cargos.xhtml";
	}
	
	public String getExcluirCargos() {
		CargoRepository cRepository = new CargoRepository();
		Cargo cargo = new Cargo();
		Integer id;
		id = Integer.parseInt(this.getId());
		
		cargo.setId(id);
		cargo.setDescricao(getDescricao());
		cRepository.delete(cargo);
		
		return "cargos.xhtml";
	}
	
	public String cargosCadastroRetorno() {
		return "cargosCadastro.xhtml";
	}
	
	public String cargosListarRetorno() {
		return "cargosListar.xhtml";
	}
	
	public String cargosAlterarRetorno() {
		return "cargosAlterar.xhtml";
	}
	
	public String cargosExcluirRetorno() {
		return "cargosExcluir.xhtml";
	}
	public String cargosCadastroVoltar() {
		return "cargos.xhtml";
	}

	public List<Cargo> getListaCargosFiltrados() {
		return listaCargosFiltrados;
	}

	public void setListaCargosFiltrados(List<Cargo> listaCargosFiltrados) {
		this.listaCargosFiltrados = listaCargosFiltrados;
	}

	public void setListaCargos(List<Cargo> listaCargos) {
		this.listaCargos = listaCargos;
	}
	
}
