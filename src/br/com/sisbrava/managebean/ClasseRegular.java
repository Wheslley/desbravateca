package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import br.com.sisbrava.bean.Classes;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.ClassesRepository;

@ManagedBean(name = "classeRegular")
public class ClasseRegular {

	private String id;
	private String descricao;
	private String idadePermitida;
	private List<Classes> listaClasses;
	private List<Classes> listaClassesFiltrados;

	public List<Classes> getListaClasses() {

		listaClasses = new ArrayList<Classes>();
		ClassesRepository cRepository = new ClassesRepository();

		List<Object> lista = cRepository.selectMultiplusObjects(ITables.CLASSES);

		for (Object o : lista) {
			Classes classes = new Classes();
			classes = (Classes) o;
			listaClasses.add(classes);
		}

		return listaClasses;
	}

	@SuppressWarnings("finally")
	public String getCadastroClasseRegular() {

		try {

			ClassesRepository cRepository = new ClassesRepository();
			Classes classe = new Classes();

			classe.setDescricao(this.descricao);
			classe.setIdade_permitida(Integer.parseInt(this.idadePermitida));

			cRepository.insert(classe);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "classesRegulares.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String buscarID() {

		try {

			Integer id;
			id = Integer.parseInt(this.getId());
			ClassesRepository cRepository = new ClassesRepository();
			Classes classes = (Classes) cRepository.getSelectOneObject(id);

			this.descricao = classes.getDescricao();
			this.idadePermitida = classes.getIdade_permitida().toString();

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "";
		}
	}

	@SuppressWarnings("finally")
	public String getAlterarClasses() {

		try {

			ClassesRepository cRepository = new ClassesRepository();
			Classes classes = (Classes) cRepository.getSelectOneObject(Integer.parseInt(getId()));

			classes.setDescricao(this.getDescricao());
			classes.setIdade_permitida(Integer.parseInt(this.getIdadePermitida()));

			cRepository.update(classes);

		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "classesRegulares.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String getExcluirClasses() {
		
		try {
			
			ClassesRepository cRepository = new ClassesRepository();
			Classes classes = new Classes();
			Integer id;
			id = Integer.parseInt(this.getId());

			classes = (Classes) cRepository.getSelectOneObject(id);
			cRepository.delete(classes);
			
		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		} finally {
			return "classesRegulares.xhtml";
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdadePermitida() {
		return idadePermitida;
	}

	public void setIdadePermitida(String idadePermitida) {
		this.idadePermitida = idadePermitida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Classes> getListaClassesFiltrados() {
		return listaClassesFiltrados;
	}

	public void setListaClassesFiltrados(List<Classes> listaClassesFiltrados) {
		this.listaClassesFiltrados = listaClassesFiltrados;
	}

	public void setListaClasses(List<Classes> listaClasses) {
		this.listaClasses = listaClasses;
	}
	
	public String classeRegularCadastroRetorno() {
		return "classesRegularesCadastro.xhtml";
	}

	public String classeRegularListarRetorno() {
		return "classesRegularesListar.xhtml";
	}

	public String classeRegularAlterarRetorno() {
		return "classesRegularesAlterar.xhtml";
	}

	public String classeRegularExcluirRetorno() {
		return "classesRegularesExcluir.xhtml";
	}

	public String classeRegularCadastroVoltar() {
		return "classesRegulares.xhtml";
	}

}
