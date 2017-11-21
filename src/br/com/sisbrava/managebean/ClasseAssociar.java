package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Classes;
import br.com.sisbrava.bean.Pessoa;
import br.com.sisbrava.bean.PessoaPossuiClasse;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.ClassesRepository;
import br.com.sisbrava.repository.PessoaPossuiClasseRepository;
import br.com.sisbrava.repository.PessoaRepository;

@ManagedBean(name = "classeAssociar")
public class ClasseAssociar {

	private Integer identificadorPessoa;
	private Integer identificadorClasse;
	private Double nota;
	private List<Classes> listaClasses = new ArrayList<>();
	private List<Classes> listaClassesFiltrados = new ArrayList<>();
	private List<Pessoa> listaPessoas = new ArrayList<>();
	private List<Pessoa> listaPessoasFiltrados = new ArrayList<>();
	private List<PessoaPossuiClasse> listaPessoaPossuiClasse = new ArrayList<>();
	private List<PessoaPossuiClasse> listaPessoaPossuiClasseFiltrados = new ArrayList<>();

	@PostConstruct
	public void init() {

		PessoaRepository pessoaRepository = new PessoaRepository();
		ClassesRepository classeRepository = new ClassesRepository();
		PessoaPossuiClasseRepository ppcRepository = new PessoaPossuiClasseRepository();

		List<Object> listaObjects = (List<Object>) pessoaRepository.selectMultiplusObjects(ITables.PESSOA);

		for (Object o : listaObjects) {
			this.listaPessoas.add((Pessoa) o);
		}

		listaObjects = (List<Object>) classeRepository.selectMultiplusObjects(ITables.CLASSES);

		for (Object o : listaObjects) {
			this.listaClasses.add((Classes) o);
		}

		listaObjects = (List<Object>) ppcRepository.selectMultiplusObjects(ITables.PESSOA_POSSUI_CLASSE);

		for (Object o : listaObjects) {
			this.listaPessoaPossuiClasse.add((PessoaPossuiClasse) o);
		}

	}

	public String associarClassePessoa() {

		Boolean validate = true;

		PessoaRepository pRepository = new PessoaRepository();
		Pessoa pessoa = (Pessoa) pRepository.getSelectOneObject(this.identificadorPessoa);

		ClassesRepository cRepository = new ClassesRepository();
		Classes classe = (Classes) cRepository.getSelectOneObject(this.identificadorClasse);

		PessoaPossuiClasseRepository ppcRepository = new PessoaPossuiClasseRepository();

		for (PessoaPossuiClasse ppClasseAux : listaPessoaPossuiClasse) {
			if (ppClasseAux.getClasse().getId() == this.identificadorClasse
					&& ppClasseAux.getPessoa().getId() == this.identificadorPessoa) {
				validate = false;
			}
		}

		if (validate) {

			PessoaPossuiClasse ppClasse = new PessoaPossuiClasse();
			ppClasse.setClasse(classe);
			ppClasse.setPessoa(pessoa);
			ppClasse.setNota(this.nota);
			ppClasse.setStatus(1);

			ppcRepository.insert(ppClasse);
			this.listaPessoaPossuiClasse.add(ppClasse);
			
			this.identificadorClasse = null;
			this.identificadorPessoa = null;
			this.nota = null;

		}
		
		return "classeRegularAssociarClasseRetorno.html";
	}
	
	public Integer getIdentificadorPessoa() {
		return identificadorPessoa;
	}

	public void setIdentificadorPessoa(Integer identificadorPessoa) {
		this.identificadorPessoa = identificadorPessoa;
	}

	public Integer getIdentificadorClasse() {
		return identificadorClasse;
	}

	public void setIdentificadorClasse(Integer identificadorClasse) {
		this.identificadorClasse = identificadorClasse;
	}

	public List<Classes> getListaClasses() {
		return listaClasses;
	}

	public void setListaClasses(List<Classes> listaClasses) {
		this.listaClasses = listaClasses;
	}

	public List<Classes> getListaClassesFiltrados() {
		return listaClassesFiltrados;
	}

	public void setListaClassesFiltrados(List<Classes> listaClassesFiltrados) {
		this.listaClassesFiltrados = listaClassesFiltrados;
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

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public List<PessoaPossuiClasse> getListaPessoaPossuiClasse() {
		return listaPessoaPossuiClasse;
	}

	public void setListaPessoaPossuiClasse(List<PessoaPossuiClasse> listaPessoaPossuiClasse) {
		this.listaPessoaPossuiClasse = listaPessoaPossuiClasse;
	}

	public List<PessoaPossuiClasse> getListaPessoaPossuiClasseFiltrados() {
		return listaPessoaPossuiClasseFiltrados;
	}

	public void setListaPessoaPossuiClasseFiltrados(List<PessoaPossuiClasse> listaPessoaPossuiClasseFiltrados) {
		this.listaPessoaPossuiClasseFiltrados = listaPessoaPossuiClasseFiltrados;
	}
	
	public String classeAssociarVoltar() {
		return "classesRegulares.xhtml";
	}
}
