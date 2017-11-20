package br.com.sisbrava.managebean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.repository.PessoaRepository;
import br.com.sisbrava.repository.UsuarioRepository;

@ManagedBean(name = "conta")
public class Conta {

	private Usuario usuario;
	    
    @PostConstruct
	public void init() {

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		HttpSession session = (HttpSession) ec.getSession(false);
		this.usuario = (Usuario) session.getAttribute("usuarioObject");
		
	}
    
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso", "Seus dados foram alterados com sucesso!"));
    }
    
	@SuppressWarnings("finally")
	public String alterarConta() {

		try {
			
			PessoaRepository pRepository = new PessoaRepository();
			pRepository.update(this.usuario.getPessoa());
			
			UsuarioRepository uRepository = new UsuarioRepository();
			uRepository.update(this.usuario);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "conta.xhtml";
		}
	}
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
