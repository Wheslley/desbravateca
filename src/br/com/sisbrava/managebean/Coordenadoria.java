package br.com.sisbrava.managebean;

import javax.faces.bean.ManagedBean;
import br.com.sisbrava.bean.Permissoes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.repository.PermissoesRepository;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;

@ManagedBean(name = "coordenadoria")
public class Coordenadoria {

	private String email;
	private String username;
	private String password;
	private String usernameCadastro;
	private String passwordCadastro;

	@SuppressWarnings("finally")
	public String getCadastroLogin() {
		
		try {
			
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
			
		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "login";
		}

	}

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

}
