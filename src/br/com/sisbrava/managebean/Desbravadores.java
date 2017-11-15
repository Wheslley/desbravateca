package br.com.sisbrava.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Permissoes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.PermissoesRepository;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;

@ManagedBean(name = "desbravadores")
public class Desbravadores {

	private String nome;
	private String idade;
	private String rg;
	private String cpf;
	private String username;
	private String password;

	public Desbravadores() {
		this.password = "senha123";
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

	public String getCadastroDesbravadores() {

		PermissoesRepository permRepository = new PermissoesRepository();
		Permissoes permissao = (Permissoes) permRepository.getSelectOneObject(2);
		
		PessoaRepository pRepository = new PessoaRepository();
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome(this.nome);
		pessoa.setIdade(Integer.parseInt(this.idade));
		pessoa.setRg(this.rg);
		pessoa.setCpf(this.cpf);
		
		pRepository.insert(pessoa);

		List<Object> listaPessoa = (List<Object>) pRepository.selectMultiplusObjects(ITables.PESSOA);
		
		Integer idPessoa = 0;
		
		for(Object o : listaPessoa) {
			Pessoa p = (Pessoa) o;
			if(p.getNome().equalsIgnoreCase(pessoa.getNome())) {
				idPessoa = p.getId();
			}
		}
				
		pessoa = (Pessoa) pRepository.getSelectOneObject(idPessoa);
		
		UsuarioRepository uRepository = new UsuarioRepository();
		
		Usuario usuario = new Usuario();
		
		usuario.setPermissao(permissao);
		usuario.setPessoa(pessoa);
		usuario.setSenha(this.password);
		usuario.setUsuario(pessoa.getNome());
		
		uRepository.insert(usuario);

		return "desbravadores.xhtml";

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
