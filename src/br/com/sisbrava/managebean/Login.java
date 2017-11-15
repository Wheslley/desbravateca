package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.Permissoes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.repository.PermissoesRepository;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;

@ManagedBean(name = "login")
public class Login {
	
	private String email;
	private String username;
	private String password;
	private String usernameCadastro;
	private String passwordCadastro;

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
	
	public String getUsernameCadastro() {
		return usernameCadastro;
	}

	public void setUsernameCadastro(String usernameCadastro) {
		this.usernameCadastro = usernameCadastro;
	}

	public String getPasswordCadastro() {
		return passwordCadastro;
	}

	public void setPasswordCadastro(String passwordCadastro) {
		this.passwordCadastro = passwordCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getCredencial() {

//		UsuarioRepository uRepository = new UsuarioRepository();
//		
//		if (uRepository.validateUsernamePassword(this.username, this.password)) {
//
//			setUsernamePermitionSession(uRepository);
//			return "paginaInicial";
//		}
//		System.out.println("Error Login.getCredencial()");
//		return "login";
		
		return "paginaInicial";

	}

	public void setUsernamePermitionSession(UsuarioRepository uRepository) {

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("usuario", this.username);

		Usuario usuario = (Usuario) uRepository.getUsuarioUsername(this.username);
		session.setAttribute("permissao", usuario.getPermissao().getDescricao());
		
	}
	
	public String getCadastroLogin() {

		UsuarioRepository uRepository = new UsuarioRepository();
		PessoaRepository pRepository = new PessoaRepository();
		PermissoesRepository permRepository = new PermissoesRepository();
		
		Usuario user = new Usuario();
		Pessoa pessoa = new Pessoa();
		Permissoes perm = new Permissoes();
		
		pessoa = (Pessoa) pRepository.getSelectOneObject(3);
		perm = (Permissoes) permRepository.getSelectOneObject(9);
		
		user.setUsuario(getUsernameCadastro());
		user.setSenha(getPasswordCadastro());
		user.setPessoa(pessoa);
		user.setPermissao(perm);

		uRepository.insert(user);
		
		return "login";

	}

}
