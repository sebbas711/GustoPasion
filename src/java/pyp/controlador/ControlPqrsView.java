/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Estadopqrs;
import pyp.modelo.entidades.Pqrs;
import pyp.servicios.IPqrsService;
import pyp.util.MessageUtil;

@Named
@ViewScoped
public class ControlPqrsView implements Serializable {

    @EJB
    private IPqrsService pqrsService;

    private List<Estadopqrs> estadoPqrsHabilitados;
    private List<Pqrs> pqrs;
    private Estadopqrs estadoPqrsFiltro;

    public ControlPqrsView() {
    }

    @PostConstruct
    public void init(){
        try {
            findPqrs();
            estadoPqrsHabilitados = pqrsService.getEstadoPqrsHabilitados();
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public String getColorByEstadoPqrs(Estadopqrs estadoPqrs) {
        return "#" + (estadoPqrs.getId()% 2 == 0 ? "951010" : "727272");
    }

    public void filtrarPorEstadoPqrs(Estadopqrs estadoPqrsFiltro) {
        this.estadoPqrsFiltro = estadoPqrsFiltro;
        findPqrs();
    }

    public void limpiarFiltroEstadoPqrs() {
        this.estadoPqrsFiltro = null;
        findPqrs();
    }

    private void findPqrs() {
        try {
            pqrs = pqrsService.findPqrsByFilter(estadoPqrsFiltro);
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public Estadopqrs getEstadopqrsFiltro() {
        return estadoPqrsFiltro;
    }

    public List<Estadopqrs> getEstadoPqrsHabilitados() {
        return estadoPqrsHabilitados;
    }

    public List<Pqrs> getPqrs() {
        return pqrs;
    }

    
}
