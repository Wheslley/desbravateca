package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.PessoaPossuiClasse;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.controller.ClasseNota;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.PessoaPossuiClasseRepository;

@ManagedBean(name = "classe")
public class Classe {

	private String nome;
	private List<ClasseNota> listaClasse = new ArrayList<>();
	private List<ClasseNota> listaClasseFiltrado = new ArrayList<>();

	@PostConstruct
	public void init() {
		
		PessoaPossuiClasseRepository ppcRepository = new PessoaPossuiClasseRepository();
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		HttpSession session = (HttpSession) ec.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuarioObject");

		List<Object> listaObjects = (List<Object>) ppcRepository.selectMultiplusObjects(ITables.PESSOA_POSSUI_CLASSE);

		for (Object o : listaObjects) {
			PessoaPossuiClasse ppclasse = (PessoaPossuiClasse) o;
			if (ppclasse.getPessoa().getId() == usuario.getPessoa().getId() && ppclasse.getStatus() == 1) {
				ClasseNota classeNota = new ClasseNota();
				classeNota.setClasse(ppclasse.getClasse());
				classeNota.setNota(ppclasse.getNota());
				this.listaClasse.add(classeNota);
			}
		}

	}

	public List<ClasseNota> getListaClasse() {
		return listaClasse;
	}

	public void setListaClasse(List<ClasseNota> listaClasse) {
		this.listaClasse = listaClasse;
	}

	public List<ClasseNota> getListaClasseFiltrado() {
		return listaClasseFiltrado;
	}

	public void setListaClasseFiltrado(List<ClasseNota> listaClasseFiltrado) {
		this.listaClasseFiltrado = listaClasseFiltrado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
