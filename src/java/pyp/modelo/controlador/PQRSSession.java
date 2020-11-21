package pyp.modelo.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import pyp.modelo.DAO.IPqrsDAO;
import pyp.modelo.entidades.Pqrs;

/**
 *
 * @author alejo
 */
@Named(value = "pQRSSession")
@SessionScoped
public class PQRSSession implements Serializable {

    /**
     * Creates a new instance of PQRSSession
     */
    public PQRSSession() {
    }
    
    @PostConstruct
    public void init() {
    }
    
    @EJB
    
    private IPqrsDAO pqrsDAO;
    private List<Pqrs> pqrs;
    private Pqrs pqrsSeleccionada;
    private Pqrs pqrsNueva;

    public List<Pqrs> getPqrs() {
        if(pqrs == null || pqrs.isEmpty()){
            pqrs = pqrsDAO.findAll();
        }
        return pqrs;
    }

    public void setPqrs(List<Pqrs> pqrs) {
        this.pqrs = pqrs;
    }

    public Pqrs getPqrsSeleccionada() {
        return pqrsSeleccionada;
    }

    public void setPqrsSeleccionada(Pqrs pqrsSeleccionada) {
        this.pqrsSeleccionada = pqrsSeleccionada;
    }

    public Pqrs getPqrsNueva() {
        return pqrsNueva;
    }

    public void setPqrsNueva(Pqrs pqrsNueva) {
        this.pqrsNueva = pqrsNueva;
    }
    
    public void seleccionarPqrs(Pqrs pqrs){
        this.pqrsSeleccionada = pqrs;
    }
    
    public void registrarPqrs(){
        pqrsDAO.create(pqrsNueva);
    }
    
}
