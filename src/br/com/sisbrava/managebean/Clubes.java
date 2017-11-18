package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import br.com.sisbrava.bean.Cep;
import br.com.sisbrava.bean.Clube;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.CepRepository;
import br.com.sisbrava.repository.ClubeRepository;

@ManagedBean(name = "clubes")
public class Clubes {

	private String id;
	private String descricao;
	private String id_cep;
	private List<Cep> listaCep = new ArrayList<>();
	private List<Cep> listaCepFiltrados;
	private List<Clube> listaClube;
	private List<Clube> listaClubesFiltrados;

	@PostConstruct
	public void init() {

		if (this.listaCep.isEmpty()) {

			CepRepository cRepository = new CepRepository();
			List<Object> listaObject = (List<Object>) cRepository.selectMultiplusObjects(ITables.CEP);

			for (Object o : listaObject) {
				Cep c = (Cep) o;
				this.listaCep.add(c);
			}

		}

	}

	@SuppressWarnings("finally")
	public List<Clube> getListaClube() {

		try {

			listaClube = new ArrayList<Clube>();
			ClubeRepository cRepository = new ClubeRepository();

			List<Object> lista = cRepository.selectMultiplusObjects(ITables.CLUBE);

			for (Object o : lista) {
				Clube clube = new Clube();
				clube = (Clube) o;
				listaClube.add(clube);
			}

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return listaClube;
		}
	}

	@SuppressWarnings("finally")
	public String getCadastroClube() {

		try {

			CepRepository cRepository = new CepRepository();
			Cep cep = (Cep) cRepository.getSelectOneObject(Integer.parseInt(this.id_cep));

			Clube clube = new Clube();
			clube.setDescricao(this.descricao);
			clube.setCep(cep);
			clube.setRegiao(cep.getRegiao());

			ClubeRepository cbRepository = new ClubeRepository();
			cbRepository.insert(clube);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "clube.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String buscarID() {

		try {

			ClubeRepository clubeRepository = new ClubeRepository();
			Clube clube = (Clube) clubeRepository.getSelectOneObject(Integer.parseInt(this.getId()));

			this.id = clube.getId().toString();
			this.descricao = clube.getDescricao();
			this.id_cep = clube.getCep().getId().toString();

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "";
		}
	}

	@SuppressWarnings("finally")
	public String getAlterarCargos() {

		try {

			ClubeRepository cRepository = new ClubeRepository();
			Clube clube = (Clube) cRepository.getSelectOneObject(Integer.parseInt(this.getId()));

			CepRepository cpRepository = new CepRepository();
			Cep cep = (Cep) cpRepository.getSelectOneObject(Integer.parseInt(this.id_cep));

			clube.setDescricao(this.getDescricao());
			clube.setCep(cep);

			cRepository.update(clube);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "clube.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String getExcluirClube() {

		try {

			ClubeRepository clubeRepository = new ClubeRepository();
			Clube clube = (Clube) clubeRepository.getSelectOneObject(Integer.parseInt(this.id));

			clubeRepository.delete(clube);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "clube.xhtml";
		}

	}

	public String getId_cep() {
		return id_cep;
	}

	public void setId_cep(String id_cep) {
		this.id_cep = id_cep;
	}

	public List<Cep> getListaCep() {
		return listaCep;
	}

	public void setListaCep(List<Cep> listaCep) {
		this.listaCep = listaCep;
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

	public List<Cep> getListaCepFiltrados() {
		return listaCepFiltrados;
	}

	public void setListaCepFiltrados(List<Cep> listaCepFiltrados) {
		this.listaCepFiltrados = listaCepFiltrados;
	}

	public List<Clube> getListaClubesFiltrados() {
		return listaClubesFiltrados;
	}

	public void setListaClubesFiltrados(List<Clube> listaClubesFiltrados) {
		this.listaClubesFiltrados = listaClubesFiltrados;
	}

	public void setListaClube(List<Clube> listaClube) {
		this.listaClube = listaClube;
	}

	public String clubeCadastroRetorno() {
		return "clubeCadastro.xhtml";
	}

	public String clubeListarRetorno() {
		return "clubeListar.xhtml";
	}

	public String clubeAlterarRetorno() {
		return "clubeAlterar.xhtml";
	}

	public String clubeExcluirRetorno() {
		return "clubeExcluir.xhtml";
	}

	public String clubeVoltar() {
		return "clube.xhtml";
	}

}
