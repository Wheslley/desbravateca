package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Especialidades;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.EspecialidadesRepository;

@ManagedBean(name = "especialidadeBean")
public class Especialidade {
	
	private String id;
	private String descricao;
	private String dificuldade;
	private List<Especialidades> listaEspecialidade;
	
	public List<Especialidades> getListaEspecialidade(){
		
		listaEspecialidade = new ArrayList<Especialidades>();
		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		
		List<Object> lista = eRepository.selectMultiplusObjects(ITables.ESPECIALIDADES);
		
		for(Object o : lista) {
			Especialidades especialidades = new Especialidades();
			especialidades = (Especialidades) o;
			listaEspecialidade.add(especialidades);
		}
		
		return listaEspecialidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getCadastroEspecialidade() {

		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidade = new Especialidades();
		
		especialidade.setDescricao(this.descricao);
		especialidade.setDificuldade(this.dificuldade);
		
		eRepository.insert(especialidade);
		
		return "especialidades.xhtml";

	}
	
	public String buscarID() {
		
		Integer id;
		id = Integer.parseInt(this.getId());
		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidades = (Especialidades) eRepository.getSelectOneObject(id);
		
		this.descricao = especialidades.getDescricao();
		this.dificuldade = especialidades.getDificuldade();
		
		return "";
	}
	
	public String getAlterarEspecialidades() {
		
		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidades = (Especialidades) eRepository.getSelectOneObject(Integer.parseInt(getId()));
		
		especialidades.setDescricao(this.getDescricao());
		especialidades.setDificuldade(this.getDificuldade());
		
		eRepository.update(especialidades);
		
		return "especialidades.xhtml";
	}
	
	public String getExcluirEspecialidades() {
		EspecialidadesRepository eRepository = new EspecialidadesRepository();
		Especialidades especialidades = new Especialidades();
		Integer id;
		id = Integer.parseInt(this.getId());
		
		especialidades.setId(id);
		especialidades.setDescricao(this.getDescricao());
		especialidades.setDificuldade(this.getDificuldade());
		eRepository.delete(especialidades);
		
		return "especialidades.xhtml";
	}
	
	public String especialidadeCadastroRetorno() {
		return "especialidadesCadastro.xhtml";
	}
	
	public String especialidadeListarRetorno() {
		return "especialidadesListar.xhtml";
	}
	
	public String especialidadeAlterarRetorno() {
		return "especialidadesAlterar.xhtml";
	}
	
	public String especialidadeExcluirRetorno() {
		return "especialidadesExcluir.xhtml";
	}
	public String especialidadeCadastroVoltar() {
		return "especialidades.xhtml";
	}

}
