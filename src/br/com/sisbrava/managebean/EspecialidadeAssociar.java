package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Especialidades;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.PessoaPossuiEspecialidade;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.EspecialidadesRepository;
import br.com.sisbrava.repository.PessoaPossuiEspecialidadeRepository;
import br.com.sisbrava.repository.PessoaRepository;

@ManagedBean(name = "especialidadeAssociar")
public class EspecialidadeAssociar {

	private Integer identificadorPessoa;
	private Integer identificadorEspecialidade;
	private Double nota;
	private List<Especialidades> listaEspecialidades = new ArrayList<>();
	private List<Especialidades> listaEspecialidadesFiltrados = new ArrayList<>();
	private List<Pessoa> listaPessoas = new ArrayList<>();
	private List<Pessoa> listaPessoasFiltrados = new ArrayList<>();
	private List<PessoaPossuiEspecialidade> listaPessoaPossuiEspec = new ArrayList<>();
	private List<PessoaPossuiEspecialidade> listaPessoaPossuiEspecFiltrados = new ArrayList<>();

	@PostConstruct
	public void init() {

		PessoaRepository pessoaRepository = new PessoaRepository();
		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		PessoaPossuiEspecialidadeRepository ppeRepository = new PessoaPossuiEspecialidadeRepository();

		List<Object> listaObjects = (List<Object>) pessoaRepository.selectMultiplusObjects(ITables.PESSOA);

		for (Object o : listaObjects) {
			this.listaPessoas.add((Pessoa) o);
		}

		listaObjects = (List<Object>) eRepository.selectMultiplusObjects(ITables.ESPECIALIDADES);

		for (Object o : listaObjects) {
			this.listaEspecialidades.add((Especialidades) o);
		}

		listaObjects = (List<Object>) ppeRepository.selectMultiplusObjects(ITables.PESSOA_POSSUI_ESPECIALIDADE);

		for (Object o : listaObjects) {
			this.listaPessoaPossuiEspec.add((PessoaPossuiEspecialidade) o);
		}

	}

	public String associarClassePessoa() {

		Boolean validate = true;

		PessoaRepository pRepository = new PessoaRepository();
		Pessoa pessoa = (Pessoa) pRepository.getSelectOneObject(this.identificadorPessoa);

		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidade = (Especialidades) eRepository.getSelectOneObject(this.identificadorEspecialidade);

		PessoaPossuiEspecialidadeRepository ppeRepository = new PessoaPossuiEspecialidadeRepository();

		for (PessoaPossuiEspecialidade ppEspecialidadeAux : listaPessoaPossuiEspec) {
			if (ppEspecialidadeAux.getEspecialidade().getId() == this.identificadorEspecialidade
					&& ppEspecialidadeAux.getPessoa().getId() == this.identificadorPessoa) {
				validate = false;
			}
		}

		if (validate) {

			PessoaPossuiEspecialidade ppEspecialidade = new PessoaPossuiEspecialidade();
			ppEspecialidade.setEspecialidade(especialidade);
			ppEspecialidade.setPessoa(pessoa);
			ppEspecialidade.setNota(this.nota);
			ppEspecialidade.setStatus(1);

			ppeRepository.insert(ppEspecialidade);
			this.listaPessoaPossuiEspec.add(ppEspecialidade);

			this.identificadorEspecialidade = null;
			this.identificadorPessoa = null;
			this.nota = null;

		}

		return "especialidadesAssociar.xhtml";
	}

	public Integer getIdentificadorPessoa() {
		return identificadorPessoa;
	}

	public void setIdentificadorPessoa(Integer identificadorPessoa) {
		this.identificadorPessoa = identificadorPessoa;
	}

	public Integer getIdentificadorEspecialidade() {
		return identificadorEspecialidade;
	}

	public void setIdentificadorEspecialidade(Integer identificadorEspecialidade) {
		this.identificadorEspecialidade = identificadorEspecialidade;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public List<Especialidades> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidades> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public List<Especialidades> getListaEspecialidadesFiltrados() {
		return listaEspecialidadesFiltrados;
	}

	public void setListaEspecialidadesFiltrados(List<Especialidades> listaEspecialidadesFiltrados) {
		this.listaEspecialidadesFiltrados = listaEspecialidadesFiltrados;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<Pessoa> getListaPessoasFiltrados() {
		return listaPessoasFiltrados;
	}

	public void setListaPessoasFiltrados(List<Pessoa> listaPessoasFiltrados) {
		this.listaPessoasFiltrados = listaPessoasFiltrados;
	}

	public List<PessoaPossuiEspecialidade> getListaPessoaPossuiEspec() {
		return listaPessoaPossuiEspec;
	}

	public void setListaPessoaPossuiEspec(List<PessoaPossuiEspecialidade> listaPessoaPossuiEspec) {
		this.listaPessoaPossuiEspec = listaPessoaPossuiEspec;
	}

	public List<PessoaPossuiEspecialidade> getListaPessoaPossuiEspecFiltrados() {
		return listaPessoaPossuiEspecFiltrados;
	}

	public void setListaPessoaPossuiEspecFiltrados(List<PessoaPossuiEspecialidade> listaPessoaPossuiEspecFiltrados) {
		this.listaPessoaPossuiEspecFiltrados = listaPessoaPossuiEspecFiltrados;
	}

	public String classeAssociarVoltar() {
		return "especialidades.xhtml";
	}

}
