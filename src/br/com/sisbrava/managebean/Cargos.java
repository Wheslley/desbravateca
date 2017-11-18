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

	
	@SuppressWarnings("finally")
	public List<Cargo> getListaCargos() {

		try {

			listaCargos = new ArrayList<Cargo>();
			CargoRepository cRepository = new CargoRepository();

			List<Object> lista = cRepository.selectMultiplusObjects(ITables.CARGO);

			for (Object o : lista) {
				Cargo cargo = new Cargo();
				cargo = (Cargo) o;
				listaCargos.add(cargo);
			}

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return listaCargos;
		}
	}

	@SuppressWarnings("finally")
	public String getCadastroCargos() {
		
		try {

			CargoRepository cRepository = new CargoRepository();
			Cargo cargo = new Cargo();

			cargo.setDescricao(getDescricao());

			cRepository.insert(cargo);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "cargos.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String buscarID() {

		try {

			Integer id;
			id = Integer.parseInt(this.getId());
			CargoRepository cRepository = new CargoRepository();
			Cargo cargo = (Cargo) cRepository.getSelectOneObject(id);

			this.setDescricao(cargo.getDescricao());

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Mensagem] " + ex.getMessage());
		} finally {
			return "";
		}

	}

	@SuppressWarnings("finally")
	public String getAlterarCargos() {

		try {

			CargoRepository cRepository = new CargoRepository();
			Cargo cargo = (Cargo) cRepository.getSelectOneObject(Integer.parseInt(this.getId()));
			cargo.setDescricao(this.getDescricao());

			cRepository.update(cargo);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Mensagem] " + ex.getMessage());
		} finally {
			return "cargos.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String getExcluirCargos() {
		try {

			CargoRepository cRepository = new CargoRepository();
			Cargo cargo = new Cargo();
			Integer id;
			id = Integer.parseInt(this.getId());

			cargo.setId(id);
			cargo.setDescricao(getDescricao());
			cRepository.delete(cargo);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Mensagem] " + ex.getMessage());
		} finally {
			return "cargos.xhtml";
		}
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
