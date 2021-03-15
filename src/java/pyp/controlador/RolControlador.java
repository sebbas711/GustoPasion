package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pyp.DAO.IRolDAO;
import pyp.modelo.entidades.Rol;

/**
 *
 * @author alejo
 */
@Named
@RequestScoped
public class RolControlador implements Serializable {

    @EJB
    private IRolDAO rDAO;
    private List<Rol> roles;
    private Rol rolSeleccionado;

    public RolControlador() {
    }

    @PostConstruct
    public void init() {

    }

    public List<Rol> getRoles() {
        if (roles == null || roles.isEmpty()) {
            roles = rDAO.findAll();
        }
        return roles;
    }

    public void seleccionarRol(Rol roles) {
        System.out.println("Se ha seleccionado el insumo");
        System.out.println(roles);
        this.rolSeleccionado = roles;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

}
