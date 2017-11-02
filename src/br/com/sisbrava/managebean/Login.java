package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.repository.UsuarioRepository;

@ManagedBean(name = "login")
public class Login {
	
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
		
		UsuarioRepository uRepository = new UsuarioRepository();
		
		if(uRepository.validateUsernamePassword(this.username, this.password)) {
			setUsernamePermitionSession(uRepository);			
			return "paginaInicial";
		}
		System.out.println("Error Login.getCredencial()");
		return "login";
		
	}
	
	public void setUsernamePermitionSession(UsuarioRepository uRepository) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("usuario", this.username);
		
		Usuario usuario = (Usuario) uRepository.getUsuarioUsername(this.username);
		session.setAttribute("permissao", usuario.getPermissao().getDescricao());
		
	}
		
}
