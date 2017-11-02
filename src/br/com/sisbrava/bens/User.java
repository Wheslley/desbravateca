package br.com.sisbrava.bens;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "user")
public class User {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCredencial() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("usuario", this.username);
		
//		UsuarioRepository uRepository = new UsuarioRepository();
//		
//		Permissoes p  = (Permissoes) uRepository.selectPermissaoUsuario();
//		session.setAttribute("permissao", p.getDescricao());
//		
//		if(uRepository.validateUsernamePassword(this.username, this.password)) {
		
		if(this.password.equals("senha123")) {
			return "paginaInicial2";
		}
		return "login";
	}
	
	public String getInicio() {
		
		return "paginaInicial2";
		
	}
	
	public String getSair() {
		
		return "login";
		
	}
	
	
	
}
