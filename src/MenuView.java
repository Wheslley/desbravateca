import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class MenuView {
    
	private String texto;
	
    public String getTexto() {
		return texto;
	}

	public void setTexto(String mensagem) {
		this.texto = mensagem;
	}

	public void save() {
		this.texto = "Sucess Save";
        addMessage("Success Save", "Data saved");
    }
     
    public void update() {
    	this.texto = "Sucess Update";
        addMessage("Success Update", "Data updated");
    }
     
    public void delete() {
    	this.texto = "Sucess Delete";
        addMessage("Success Delete", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}