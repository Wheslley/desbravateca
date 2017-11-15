package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Cep;
import br.com.sisbrava.bean.Clube;
import br.com.sisbrava.interfaces.mensagem.ITables;
import br.com.sisbrava.repository.CepRepository;
import br.com.sisbrava.repository.ClubeRepository;

@ManagedBean(name = "clubes")
public class Clubes {

	private String id;
	private String descricao;
	private String regiao;
	private String cidade;
	private String estado;
	private String pais;
	private List<Clube> listaClube;
	
	public List<Clube> getListaClube(){
		
		listaClube = new ArrayList<Clube>();
		ClubeRepository cRepository = new ClubeRepository();
		
		List<Object> lista = cRepository.selectMultiplusObjects(ITables.CLUBE);
		
		for(Object o : lista) {
			Clube clube = new Clube();
			clube = (Clube) o;
			listaClube.add(clube);
		}
		
		return listaClube;
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

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCadastroClube() {
		
		CepRepository cepRepository = new CepRepository();
		Cep cep = new Cep();
		
		cep.setCidade(this.cidade);
		cep.setEstado(this.estado);
		cep.setPais(this.pais);
		
		cepRepository.insert(cep);
		
		ClubeRepository clubeRepository =  new ClubeRepository();
		Clube clube = new Clube();
		
		clube.setDescricao(this.descricao);
		clube.setRegiao(Integer.parseInt(this.regiao));

		List<Object> listaCep = (List<Object>) cepRepository.selectMultiplusObjects(ITables.CEP);

		Integer idCep = 0;

		for (Object o : listaCep) {
			Cep c = (Cep) o;
			if (c.getCidade().equalsIgnoreCase(cep.getCidade())) {
				idCep = c.getId();
			}
		}
		
		cep = (Cep) cepRepository.getSelectOneObject(idCep);
		
		clube.setCep(cep);

		clubeRepository.insert(clube);

		return "clube.xhtml";

	}
	
	public String buscarID() {
		
		Integer id;
		id = Integer.parseInt(this.getId());
		ClubeRepository clubeRepository =  new ClubeRepository();
		Clube clube = (Clube) clubeRepository.getSelectOneObject(id);
		
		this.descricao = clube.getDescricao();
		this.regiao = clube.getRegiao().toString();
		
		return "";
	}
	
	public String getExcluirClube() {
		ClubeRepository clubeRepository =  new ClubeRepository();
		Clube clube = new Clube();
		Integer id;
		id = Integer.parseInt(this.getId());
		
		clube.setId(id);
		clube.setDescricao(this.getDescricao());
		clube.setRegiao(Integer.parseInt(this.getRegiao()));
		clubeRepository.delete(clube);
		
		return "clube.xhtml";
	}

	public String clubeCadastroRetorno() {
		return "clubeCadastro.xhtml";
	}
	
	public String clubeListarRetorno() {
		return "clubeListar.xhtml";
	}
	
	public String clubeAlterarRetorno() {
		return "clubeAlterar.xhtml";
	}
	
	public String clubeExcluirRetorno() {
		return "clubeExcluir.xhtml";
	}
	public String clubeCadastroVoltar() {
		return "clube.xhtml";
	}

}