package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pyp.modelo.DAO.IRolDAO;
import pyp.modelo.entidades.Rol;

/**
 *
 * @author alejo
 */
@Named(value = "rolControlador")
@RequestScoped
public class RolControlador implements Serializable{
    
    @EJB
    private IRolDAO rDAO;
    private List<Rol> roles;

    public RolControlador() {
    }
    
    @PostConstruct
    public void init(){
        
    }

    public List<Rol> getRoles() {
        if(roles == null || roles.isEmpty()){
            roles = rDAO.findAll();
        }
        return roles;
    }
    
    
}
