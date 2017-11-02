package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "paginaInicial")
public class PaginaInicial {

	public String getInicio() {

		return "paginaInicial";

	}

	public String getSair() {

		return "login";

	}

}
