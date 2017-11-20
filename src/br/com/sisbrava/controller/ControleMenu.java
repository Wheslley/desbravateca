package br.com.sisbrava.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "logic")
public class ControleMenu {

	private String controleMenu = "";

	public String getControle() {
		return controleMenu;
	}
	
	public void setControle(String controleMenu) {
		this.controleMenu = controleMenu;
	}
	
	/**
	 * Cargos
	 * */
	
	public void cargos() {
		this.controleMenu = "cargos.xhtml";
	}

	public String cargosRetorno() {
		return "cargos.xhtml";
	}
	
	/**
	 * Desbravadores
	 * */
	
	public void desbravadores() {
		this.controleMenu = "desbravadores.xhtml";
	}

	public String desbravadoresRetorno() {
		return "desbravadores.xhtml";
	}
	
	/**
	 * Especialidades
	 * */
	
	public void especialidades() {
		this.controleMenu = "especialidades.xhtml";
	}
	
	public String especialidadesRetorno() {
		return "especialidades.xhtml";
	}
	
	/**
	 * Classes Regulares e Avançadas
	 * */
	
	public void classesRegulares() {
		this.controleMenu = "classesRegulares.xhtml";
	}

	public String classesRegularesRetorno() {
		return "classesRegulares.xhtml";
	}
	
	/**
	 * Clubes
	 * */
	
	public void clube() {
		this.controleMenu = "clube.xhtml";
	}

	public String clubeRetorno() {
		return "clube.xhtml";
	}
	
	/**
	 * Coordenadoria
	 * */
	
	public void coordenadoria() {
		this.controleMenu = "coordenadoria.xhtml";
	}

	public String coordenadoriaRetorno() {
		return "coordenadoria.xhtml";
	}
	
	/**
	 * Acesso ao Sistema
	 * */
	
	public void conta() {
		this.controleMenu = "conta.xhtml";
	}

	public String contaRetorno() {
		return "conta.xhtml";
	}
	
	/**
	 * Acesso ao Evento
	 * */
	
	public void evento() {
		this.controleMenu = "eventos.xhtml";
	}

	public String eventoRetorno() {
		return "eventos.xhtml";
	}
	
	/**
	 * Classes
	 * */
	
	public void classes() {
		this.controleMenu = "classes.xhtml";
	}

	public String classesRetorno() {
		return "classes.xhtml";
	}
	
}
