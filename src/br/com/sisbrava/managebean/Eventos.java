package br.com.sisbrava.managebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sisbrava.bean.Evento;
import br.com.sisbrava.bean.TipoEvento;
import br.com.sisbrava.repository.EventoRepository;
import br.com.sisbrava.repository.TipoEventoRepository;

@ManagedBean(name = "eventos")
public class Eventos {

	private String id;
	private String descricao;
	private String taxa;
	private String tipoEvento;
	private List<Evento> listaEventos;
	private List<Evento> listaEventosFiltrados;

	@SuppressWarnings("finally")
	public List<Evento> getListaEventos() {

		try {

			listaEventos = new ArrayList<Evento>();
			EventoRepository eRepository = new EventoRepository();

			List<Object> lista = eRepository.selectMultiplusObjects("Evento");

			for (Object o : lista) {
				Evento evento = new Evento();
				evento = (Evento) o;
				listaEventos.add(evento);
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return listaEventos;
		}
	}

	@SuppressWarnings("finally")
	public String getCadastroEventos() {

		try {

			EventoRepository eRepository = new EventoRepository();
			Evento evento = new Evento();
			TipoEvento tipoEvento = (TipoEvento) TipoEventoRepository.getInstance().getSelectOneObject(1);

			evento.setDescricao(getDescricao());
			evento.setTaxa(Double.parseDouble(getTaxa()));
			evento.setTevento(tipoEvento);

			eRepository.insert(evento);

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "eventos.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String buscarID() {

		try {

			Integer id;
			id = Integer.parseInt(this.getId());

			EventoRepository eRepository = new EventoRepository();
			Evento evento = (Evento) eRepository.getSelectOneObject(id);

			this.setDescricao(evento.getDescricao());
			this.setTaxa(evento.getTaxa().toString());
			this.setTipoEvento(evento.getTevento().toString());

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "";
		}
	}

	@SuppressWarnings("finally")
	public String getAlterarEventos() {

		try {

			EventoRepository eRepository = new EventoRepository();
			Evento evento = (Evento) eRepository.getSelectOneObject(Integer.parseInt(this.getId()));
			evento.setDescricao(this.getDescricao());
			evento.setTaxa(Double.parseDouble(this.getTaxa()));

			TipoEvento tipoEvento = (TipoEvento) TipoEventoRepository.getInstance().getSelectOneObject(1);
			evento.setTevento(tipoEvento);

			eRepository.update(evento);

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "eventos.xhtml";
		}
	}

	@SuppressWarnings("finally")
	public String getExcluirEventos() {

		try {

			EventoRepository eRepository = new EventoRepository();
			Evento evento = new Evento();

			evento = (Evento) eRepository.getSelectOneObject(Integer.parseInt(this.getId()));

			eRepository.delete(evento);

		} catch (Exception ex) {
			System.out.println(ex.getClass() + " [Message] " + ex.getMessage());
		} finally {
			return "eventos.xhtml";
		}
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

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public List<Evento> getListaEventosFiltrados() {
		return listaEventosFiltrados;
	}

	public void setListaEventosFiltrados(List<Evento> listaEventosFiltrados) {
		this.listaEventosFiltrados = listaEventosFiltrados;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

}
