package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sisbrava.bean.PessoaPossuiEspecialidade;
import br.com.sisbrava.bean.Usuario;
import br.com.sisbrava.controller.EspecialidadeNota;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.PessoaPossuiEspecialidadeRepository;

@ManagedBean(name = "especialidade")
public class Especialidade {

	private List<EspecialidadeNota> listaEspecialidade = new ArrayList<>();
	private List<EspecialidadeNota> listaEspecialidadeFiltrados = new ArrayList<>();

	@PostConstruct
	public void init() {

		PessoaPossuiEspecialidadeRepository ppeRepository = new PessoaPossuiEspecialidadeRepository();

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		HttpSession session = (HttpSession) ec.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuarioObject");

		List<Object> listaObjects = (List<Object>) ppeRepository
				.selectMultiplusObjects(ITables.PESSOA_POSSUI_ESPECIALIDADE);

		for (Object o : listaObjects) {
			PessoaPossuiEspecialidade ppEspecialidade = (PessoaPossuiEspecialidade) o;
			if (ppEspecialidade.getPessoa().getId() == usuario.getPessoa().getId()
					&& ppEspecialidade.getStatus() == 1) {
				EspecialidadeNota especialidadeNota = new EspecialidadeNota();
				especialidadeNota.setEspecialidade(ppEspecialidade.getEspecialidade());
				especialidadeNota.setNota(ppEspecialidade.getNota());
				this.listaEspecialidade.add(especialidadeNota);
			}
		}
	}

	public List<EspecialidadeNota> getListaEspecialidade() {
		return listaEspecialidade;
	}

	public void setListaEspecialidade(List<EspecialidadeNota> listaEspecialidade) {
		this.listaEspecialidade = listaEspecialidade;
	}

	public List<EspecialidadeNota> getListaEspecialidadeFiltrados() {
		return listaEspecialidadeFiltrados;
	}

	public void setListaEspecialidadeFiltrados(List<EspecialidadeNota> listaEspecialidadeFiltrados) {
		this.listaEspecialidadeFiltrados = listaEspecialidadeFiltrados;
	}

}
