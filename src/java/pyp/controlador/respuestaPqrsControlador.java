
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.DAO.IRespuestapqrsDAO;
import pyp.DAO.ITipoInsumoDAO;
import pyp.modelo.entidades.Respuestapqrs;
import pyp.modelo.entidades.TipoInsumo;

/**
 *
 * @GAES 1
 */
@Named(value = "respuestaPqrsControlador")
@ViewScoped
public class respuestaPqrsControlador implements Serializable {
    
    @EJB
    private IRespuestapqrsDAO rpDAO;
    private List<Respuestapqrs> respuestaPqrs;
    
            
    public respuestaPqrsControlador() {
    } 
    
    @PostConstruct
    public void init(){
        
    }

    public List<Respuestapqrs> getRespuestaPqrs() {
        if(respuestaPqrs == null || respuestaPqrs.isEmpty()){
            respuestaPqrs = rpDAO.findAll();
        }
        return respuestaPqrs;
    }
  
    
}
