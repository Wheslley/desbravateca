package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Permissoes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.PermissoesRepository;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;
import br.com.sisbrava.utilidades.EmailNotification;

@ManagedBean(name = "desbravadores")
public class Desbravadores {

	private String id;
	private String email;
	private String nome;
	private String idade;
	private String rg;
	private String cpf;
	private Usuario usuario;
	private Pessoa pessoa;
	private Permissoes permissoes;
	private List<Pessoa> listaDesbravadores;
	private List<Pessoa> listaDesbravadoresFiltrados;
	private List<Permissoes> listaPermissoes;
	private String tipoPessoa;
	
	@SuppressWarnings("finally")
	public String buscarID() {

		try {

			Integer id;
			id = Integer.parseInt(this.getId());
			PessoaRepository pRepository = new PessoaRepository();
			Pessoa pessoa = (Pessoa) pRepository.getSelectOneObject(id);

			this.setNome(pessoa.getNome());
			this.setIdade(pessoa.getIdade().toString());
			this.setRg(pessoa.getRg());
			this.setCpf(pessoa.getCpf());

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "";
		}
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@SuppressWarnings("finally")
	public String getAlterarDesbravadores() {

		try {

			PessoaRepository pRepository = new PessoaRepository();
			Pessoa pessoa = (Pessoa) pRepository.getSelectOneObject(Integer.parseInt(getId()));

			pessoa.setNome(this.nome);
			pessoa.setIdade(Integer.parseInt(this.idade));
			pessoa.setRg(this.rg);
			pessoa.setCpf(this.cpf);

			pRepository.update(pessoa);

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "desbravadores.xhtml";
		}

	}

	@SuppressWarnings("finally")
	public String getExcluirDesbravadores() {

		try {

			PessoaRepository pRepository = new PessoaRepository();
			Pessoa pessoa = (Pessoa) pRepository.getSelectOneObject(6);

			System.out.println(pessoa.getNome());

			UsuarioRepository uRepository = new UsuarioRepository();

			List<Object> lista = uRepository.selectMultiplusObjects(ITables.USUARIO);

			for (Object o : lista) {

				Usuario usuario = (Usuario) o;
				System.out.println(usuario.getPessoa().getNome());

				if (usuario.getPessoa().getNome().equals(pessoa.getNome())) {
					uRepository.delete(usuario);
					pRepository.delete(pessoa);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "desbravadores.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public List<Pessoa> getListaDesbravadores() {

		try {

			listaDesbravadores = new ArrayList<Pessoa>();
			PessoaRepository pRepository = new PessoaRepository();

			List<Object> lista = pRepository.selectMultiplusObjects(ITables.PESSOA);

			for (Object o : lista) {
				Pessoa pessoa = new Pessoa();
				pessoa = (Pessoa) o;
				listaDesbravadores.add(pessoa);
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return listaDesbravadores;
		}
	}

	@SuppressWarnings("finally")
	public String getCadastroDesbravadores() {

		try {

			PermissoesRepository permRepository = new PermissoesRepository();
			Permissoes permissao = (Permissoes) permRepository.getSelectOneObject(Integer.parseInt(this.tipoPessoa));

			PessoaRepository pRepository = new PessoaRepository();
			Pessoa pessoa = new Pessoa();

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

			UsuarioRepository uRepository = new UsuarioRepository();
			Usuario usuario = new Usuario();

			usuario.setPermissao(permissao);
			usuario.setPessoa(pessoa);
			usuario.setUsuario(this.email);
			uRepository.insert(usuario);

			EmailNotification email = new EmailNotification();
			email.envioEmailConfirmacaoCadastro(this.email, this.nome);

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "desbravadores.xhtml";
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Permissoes> getListaPermissoes() {
		return listaPermissoes;
	}

	public void setListaPermissoes(List<Permissoes> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}

	public void setListaDesbravadores(List<Pessoa> listaDesbravadores) {
		this.listaDesbravadores = listaDesbravadores;
	}

	public List<Pessoa> getListaDesbravadoresFiltrados() {
		return listaDesbravadoresFiltrados;
	}

	public void setListaDesbravadoresFiltrados(List<Pessoa> listaDesbravadoresFiltrados) {
		this.listaDesbravadoresFiltrados = listaDesbravadoresFiltrados;
	}

	public String desbravadoresCadastroRetorno() {
		return "desbravadoresCadastro.xhtml";
	}

	public String desbravadoresListarRetorno() {
		return "desbravadoresListar.xhtml";
	}

	public String desbravadoresAlterarRetorno() {
		return "desbravadoresAlterar.xhtml";
	}

	public String desbravadoresExcluirRetorno() {
		return "desbravadoresExcluir.xhtml";
	}

	public String desbravadoresCadastroVoltar() {
		return "desbravadores.xhtml";
	}

}
