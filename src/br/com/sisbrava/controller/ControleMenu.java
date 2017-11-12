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
	
			public String cargosRetorno() {
				return "cargos.xhtml";
			}
	
	public void desbravadores() {
		this.controleMenu = "desbravadores.xhtml";
	}
	
			public String desbravadoresRetorno() {
				return "desbravadores.xhtml";
			}
	
	public void especialidades() {
		this.controleMenu = "especialidades.xhtml";
	}
	
			public String especialidadesRetorno() {
				return "especialidades.xhtml";
			}
	
	public void classesRegulares() {
		this.controleMenu = "classesRegulares.xhtml";
	}
	
			public String classesRegularesRetorno() {
				return "classesRegulares.xhtml";
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
	
			public String clubeRetorno() {
				return "clube.xhtml";
			}
	
	public void coordenadoria() {
		this.controleMenu = "coordenadoria.xhtml";
	}
	
			public String coordenadoriaRetorno() {
				return "coordenadoria.xhtml";
			}
	
	public void eventos() {
		this.controleMenu = "eventos.xhtml";
	}
	
	public void login() {
		this.controleMenu = "cadLogin.xhtml";
	}
}














