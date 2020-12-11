package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import pyp.modelo.DAO.IInsumoDAO;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.util.MessageUtil;

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
            Date fechaactual = new Date();
            if (nuevoInsumo.getFechaVencimiento().after(fechaactual) && nuevoInsumo.getTipoInsumo() != null 
                    && nuevoInsumo.getAuxCocina() != null && nuevoInsumo.getNombre() != null 
                    && nuevoInsumo.getDescripcion()!= null) {
                nuevoInsumo.setFechaIngreso(new Date());
                nuevoInsumo.setEstado(Short.valueOf("1"));
                insumos.clear();
                insumos.addAll(IDAO.findAll());
                IDAO.create(nuevoInsumo);
                mensajeRequest = "swal('Registro Exitoso', '', 'success');";
                MessageUtil.sendInfo(null, "Registro Exitoso",
                        "Listado en Control de Insumos", Boolean.FALSE);
            } else {
                MessageUtil.sendInfo(null, "Fecha de Vencimiento debe ser superior a la fecha de registro",
                        "Por favor diligencie todos los campos", Boolean.FALSE);
            }
        } catch (Exception ex) {
            System.out.println("Error UsuarioControlador:registrar " + ex.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoInsumo = new Insumo();
    }

    public void eliminarInsumo() {
        String mensajeRequest = "";
        try {
            IDAO.remove(insumoSeleccionado);
            mensajeRequest = "swal('Insumo Eliminado', 'Correctamente', 'success');";
            insumos = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar el Insumo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void actualizar() {

        String mensajeRequest = "";
        try {
            if (insumoSeleccionado != null) {
                IDAO.edit(insumoSeleccionado);
                mensajeRequest = "swal('Actualizado', 'Correctamente', 'success');";
                insumos = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo modificar la informacion del insumo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void bloquearODesbloquear() {
        String mensajeRequest = "";
        try {
            if (insumoSeleccionado != null) {
                if (insumoSeleccionado.getEstado() != 0) {
                    insumoSeleccionado.setEstado((short) 0);
                } else {
                    insumoSeleccionado.setEstado((short) 1);
                }
                IDAO.edit(insumoSeleccionado);
                mensajeRequest = "swal('Estado el Usuario', 'Modificado', 'success');";
                insumos = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo cambiar el estado del usuario', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public boolean renderedBtnBloquear(Insumo insumo) {
        return (insumo.getEstado() != 0);
    }

    public String getBtnValueBloquear() {
        if (insumoSeleccionado != null) {
            if (insumoSeleccionado.getEstado() == 0) {
                return "Desbloquear";
            }
            return "Bloquear";
        }
        return "";
    }

    private Date Date(int par, int par1, int par2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Date after(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
