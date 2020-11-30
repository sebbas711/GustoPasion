package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import pyp.modelo.DAO.IInsumoDAO;
import pyp.modelo.entidades.Insumo;

/**
 *
 * @author PC
 */
@Named(value = "inventarioControlador")
@ViewScoped
public class inventarioControlador implements Serializable {

    @EJB

    private IInsumoDAO IDAO;
    private List<Insumo> insumos;
    private Insumo insumoSeleccionado;
    private Insumo nuevoInsumo;

    public inventarioControlador() {
    }

    @PostConstruct
    public void init() {
        nuevoInsumo = new Insumo();

    }

    public List<Insumo> getInsumos() {
        if (insumos == null || insumos.isEmpty()) {
            insumos = IDAO.findAll();
        }
        return insumos;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public Insumo getNuevoInsumo() {
        return nuevoInsumo;
    }

    public void setNuevoInsumo(Insumo nuevoInsumo) {
        this.nuevoInsumo = nuevoInsumo;
    }

    public void seleccionarInsumo(Insumo i) {
        System.out.println("Se ha seleccionado el insumo");
        System.out.println(i);
        this.insumoSeleccionado = i;
    }

    public void registrar() {
        String mensajeRequest = "";
        try {
            nuevoInsumo.setEstado(Short.valueOf("1"));
            IDAO.create(nuevoInsumo);
            mensajeRequest = "swal('Registro Exitoso', '', 'success');";
        } catch (Exception ex) {
            System.out.println("Error UsuarioControlador:registrar " + ex.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoInsumo = new Insumo();
    }

}
