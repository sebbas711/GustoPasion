
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.DAO.ITipoInsumoDAO;
import pyp.modelo.entidades.TipoInsumo;

/**
 *
 * @GAES 1
 */
@Named(value = "tipoInsumoControlador")
@ViewScoped
public class TipoInsumoControlador implements Serializable {
    
    @EJB
    private ITipoInsumoDAO tiDAO;
    private List<TipoInsumo> tiposInsumo;
    
            
    public TipoInsumoControlador() {
    } 
    
    @PostConstruct
    public void init(){
        
    }

    public List<TipoInsumo> getTiposInsumo() {
        if(tiposInsumo == null || tiposInsumo.isEmpty()){
            tiposInsumo = tiDAO.findAll();
        }
        return tiposInsumo;
    }
  
    
}
