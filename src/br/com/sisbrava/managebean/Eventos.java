package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import br.com.sisbrava.bean.Evento;
import br.com.sisbrava.bean.TipoEvento;
import br.com.sisbrava.repository.EventoRepository;
import br.com.sisbrava.repository.TipoEventoRepository;

@ManagedBean(name = "eventos")
public class Eventos {

	private String descricao;
	private Double taxa;
	private Integer tipoEvento;
	private List<Evento> listaEventos = new ArrayList<>();
	private List<Evento> listaEventosFiltrados = new ArrayList<>();

	@PostConstruct
	public void init() {

		EventoRepository eventoRepository = new EventoRepository();

		List<Object> listaObjects = (List<Object>) eventoRepository.selectMultiplusObjects("Evento");

		for (Object o : listaObjects) {
			this.listaEventos.add((Evento) o);
		}

	}

	public String cadastroEventos() {

		try {
			
			Evento evento = new Evento();
			evento.setDescricao(this.descricao);
			evento.setTaxa(this.taxa);

			TipoEventoRepository tpRepository = new TipoEventoRepository();
			TipoEvento tipoEvento = (TipoEvento) tpRepository.getSelectOneObject(this.tipoEvento);

			evento.setTevento(tipoEvento);

			EventoRepository eRepository = new EventoRepository();
			eRepository.insert(evento);
			
		} catch (Exception ex) {
			System.out.println(ex.getCause() + " [Message] " + ex.getMessage());
		}

		return "eventos.xhtml";
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public List<Evento> getListaEventosFiltrados() {
		return listaEventosFiltrados;
	}

	public void setListaEventosFiltrados(List<Evento> listaEventosFiltrados) {
		this.listaEventosFiltrados = listaEventosFiltrados;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Integer getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String eventosCadastroRetorno() {
		return "eventosCadastro.xhtml";
	}

	public String eventosListarRetorno() {
		return "eventosListar.xhtml";
	}

	public String eventosAlterarRetorno() {
		return "eventosAlterar.xhtml";
	}

	public String eventosExcluirRetorno() {
		return "eventosExcluir.xhtml";
	}

	public String eventosCadastroVoltar() {
		return "eventos.xhtml";
	}

}
