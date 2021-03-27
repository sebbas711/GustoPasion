/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pyp.DAO.IEstadopqrsDAO;
import pyp.controlador.sesion.SessionControlador;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Estadopqrs;
import pyp.modelo.entidades.Pqrs;
import pyp.modelo.entidades.Respuestapqrs;
import pyp.servicios.IPqrsService;
import pyp.servicios.pqrs.IRespondePqrsService;
import pyp.util.MessageUtil;

/**
 *
 * @GAES 1
 */
@Named
@ViewScoped
public class ControlPqrsView implements Serializable {

    @Inject
    private SessionControlador sessionControlador;

    @EJB
    private IPqrsService pqrsService;
    @EJB
    private IRespondePqrsService respondePqrsService;

    private Integer idAdministrador;
    private List<Estadopqrs> estadoPqrsHabilitados;
    private List<Pqrs> pqrs;
    private Estadopqrs estadoPqrsFiltro;
    private Pqrs pqrsSeleccionada;
    private Respuestapqrs respuestaPqrs;

    public ControlPqrsView() {
    }

    @PostConstruct
    public void init() {
        try {
            findPqrs();
            estadoPqrsHabilitados = pqrsService.getEstadoPqrsHabilitados();
            idAdministrador = sessionControlador.getUser().getAdministrador().getId();
            findPqrs();
            inicializarRespuesta();
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public void seleccionarPqrs(Pqrs pqrsSeleccionada) {
        this.pqrsSeleccionada = pqrsSeleccionada;
        inicializarRespuesta();
    }

    private void inicializarRespuesta() {
        respuestaPqrs = new Respuestapqrs();
        respuestaPqrs.setPqrs(pqrsSeleccionada);
    }

    public String getColorByEstadoPqrs(Estadopqrs estadoPqrs) {
        return "#" + (estadoPqrs.getId() % 2 == 0 ? "951010" : "727272");
    }

    public void filtrarPorEstadoPqrs(Estadopqrs estadoPqrsFiltro) {
        this.estadoPqrsFiltro = estadoPqrsFiltro;
        findPqrs();
    }

    public void limpiarFiltroEstadoPqrs() {
        this.estadoPqrsFiltro = null;
        findPqrs();
    }

    public void responder() {
        try {
            respondePqrsService.responder(pqrsSeleccionada, respuestaPqrs);
            init();
            MessageUtil.sendInfoModal("Se ha respondido la PQRS", "El estado cambiado automaticamente a finalizado");
        } catch (BusinessException ex) {
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
            MessageUtil.sendInfo(null, "Error",
                    "", Boolean.FALSE);
        }
    }

    public boolean renderEditButton(Pqrs pqrs) {
        return !Objects.equals(pqrs.getEstadoPqrs().getId(), IEstadopqrsDAO.ID_ESTADO_FINALIZADA);
    }

    private void findPqrs() {
        try {
            pqrs = pqrsService.findPqrsByAdmin(idAdministrador, estadoPqrsFiltro);
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

    public Pqrs getPqrsSeleccionada() {
        return pqrsSeleccionada;
    }

    public Respuestapqrs getRespuestaPqrs() {
        return respuestaPqrs;
    }

}
