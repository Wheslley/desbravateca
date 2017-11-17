package br.com.sisbrava.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "paginaInicial")
public class PaginaInicial {

	private List<String> listaSession;

	public String getInicio() {
		return "paginaInicial";
	}

	public String getSair() {
		return "login";
	}

	public String getEventos() {
		return "eventos";
	}

	public List<String> getListaSession() {
		return listaSession;
	}

	public void setListaSession(List<String> listaSession) {
		this.listaSession = listaSession;
	}

}
