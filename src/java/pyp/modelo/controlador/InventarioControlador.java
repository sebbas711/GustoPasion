/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;
import pyp.modelo.DAO.IInsumoDAO;
import pyp.modelo.entidades.Insumo;

/**
 *
 * @author HP 15
 */
@Named(value = "inventarioControlador")
@SessionScoped
public class InventarioControlador implements Serializable {

    @EJB
    private IInsumoDAO IDAO;
    private List<Insumo> insumos;
    private List<Insumo> carnes;
    private Insumo nuevoInsumo;
    private Insumo insumoSeleccionado;
    
    /**
     * Creates a new instance of InventarioControlador
     */
    public InventarioControlador() {
          }
    @PostConstruct
    public void init(){
        nuevoInsumo = new Insumo();
    }

    public List<Insumo> getInsumos() {
        if (insumos == null || insumos.isEmpty()) {
            insumos = IDAO.findAll();

        }
        return insumos;
    }
    
   //public List<Insumo> getCarnes(){
       // if(carnes == null || carnes.isEmpty()){
            //Carnes = IDAO.findRange(2);
    //}
        //return carnes;
    //}
    
    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
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

    public Insumo getNuevoInsumo() {
        return nuevoInsumo;
    }

    public void setNuevoInsumo(Insumo nuevoInsumo) {
        this.nuevoInsumo = nuevoInsumo;
    }

}
