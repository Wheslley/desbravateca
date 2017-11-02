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
	
	public void cronogramaAnual() {
		this.controleMenu = "cronogramaAnual.xhtml";
	}
	
	public void progressoRanking() {
		this.controleMenu = "progressoRanking.xhtml";
	}
	
	public void diretoria() {
		this.controleMenu = "diretoria.xhtml";
	}
	
	public void cargos() {
		this.controleMenu = "cargos.xhtml";
	}
	
	public void desbravadores() {
		this.controleMenu = "desbravadores.xhtml";
	}
	
	public void especialidades() {
		this.controleMenu = "especialidades.xhtml";
	}
	
	public void classesRegulares() {
		this.controleMenu = "classesRegulares.xhtml";
	}
	
	public void classesAvancadas() {
		this.controleMenu = "classesAvancadas.xhtml";
	}
	
	public void ranking() {
		this.controleMenu = "ranking.xhtml";
	}
	
	public void clube() {
		this.controleMenu = "clube.xhtml";
	}
	
	public void coordenadoria() {
		this.controleMenu = "coordenadoria.xhtml";
	}
	
	public void eventos() {
		this.controleMenu = "eventos.xhtml";
	}
	
	public void login() {
		this.controleMenu = "cadLogin.xhtml";
	}
}














