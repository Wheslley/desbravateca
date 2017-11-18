package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.Permissoes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.PermissoesRepository;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;
import br.com.sisbrava.utilidades.EmailNotification;

@ManagedBean(name = "login")
public class Login {

	private String email;
	private String emailCadastro;
	private String password;
	private String nome;
	private String idade;
	private String rg;
	private String cpf;
	private Usuario usuario;
	private Pessoa pessoa;
	private Permissoes permissoes;
	private List<Permissoes> listaPermissoes;

	@SuppressWarnings("finally")
	public List<Permissoes> getListaPermissoes() {

		try {

			listaPermissoes = new ArrayList<Permissoes>();
			PermissoesRepository pRepository = new PermissoesRepository();

			List<Object> lista = pRepository.selectMultiplusObjects(ITables.PERMISSOES);

			for (Object o : lista) {
				Permissoes permissoes = new Permissoes();
				permissoes = (Permissoes) o;
				listaPermissoes.add(permissoes);
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return listaPermissoes;
		}
	}

	@SuppressWarnings("finally")
	public String getCredencial() {

		try {

			UsuarioRepository uRepository = new UsuarioRepository();

			if (uRepository.validateUsernamePassword(this.email, this.password)) {
				setUsernamePermitionSession(uRepository);
				return "paginaInicial";
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "login";
		}

	}

	public void setUsernamePermitionSession(UsuarioRepository uRepository) {

		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.email);

			Usuario usuario = (Usuario) uRepository.getUsuarioUsername(this.email);
			session.setAttribute("permissao", usuario.getPermissao().getDescricao());

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		}
	}

	@SuppressWarnings("finally")
	public String getCadastroLogin() {
		
		try {
			
			UsuarioRepository uRepository = new UsuarioRepository();
			PessoaRepository pRepository = new PessoaRepository();
			PermissoesRepository permRepository = new PermissoesRepository();

			Usuario usuario = new Usuario();
			Pessoa pessoa = new Pessoa();
			Permissoes permissao = new Permissoes();

			pessoa.setNome(this.nome);
			pessoa.setIdade(Integer.parseInt(this.idade));
			pessoa.setRg(this.rg);
			pessoa.setCpf(this.cpf);
			pRepository.insert(pessoa);

			List<Object> listaPessoa = (List<Object>) pRepository.selectMultiplusObjects(ITables.PESSOA);

			Integer idPessoa = 0;

			for (Object o : listaPessoa) {
				Pessoa p = (Pessoa) o;
				if (p.getNome().equalsIgnoreCase(pessoa.getNome())) {
					idPessoa = p.getId();
				}
			}

			pessoa = (Pessoa) pRepository.getSelectOneObject(idPessoa);

			permissao = (Permissoes) permRepository.getSelectOneObject(1);

			usuario.setUsuario(this.emailCadastro);
			usuario.setPessoa(pessoa);
			usuario.setPermissao(permissao);
			uRepository.insert(usuario);

			EmailNotification email = new EmailNotification();
			email.envioEmailConfirmacaoCadastro(this.emailCadastro, this.nome);
			
		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "login";
		}

	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Permissoes getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Permissoes permissoes) {
		this.permissoes = permissoes;
	}
	
	public void setListaPermissoes(List<Permissoes> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}
	
	public String getEmailCadastro() {
		return emailCadastro;
	}

	public void setEmailCadastro(String emailCadastro) {
		this.emailCadastro = emailCadastro;
	}

}
