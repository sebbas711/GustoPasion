/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador.usuario;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.StringUtils;
import pyp.controlador.sesion.SessionControlador;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.usuarios.IActualizacionUsuarioService;
import pyp.servicios.usuarios.IConsultaUsuarioService;
import pyp.util.MessageUtil;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class ModificaContrasenaControlador implements Serializable {

    @Inject
    private SessionControlador sessionControlador;

    @EJB
    private IActualizacionUsuarioService actualizacionUsuarioService;

    private Integer idUsuario;
    private String claveActual;
    private String nuevaClave;
    private String confirmacionNuevaClave;

    @PostConstruct
    public void init() {
        idUsuario = sessionControlador.getUser().getId();
    }

    public void actualizar() {
        try {
            if (camposValidos()) {
                actualizacionUsuarioService.actualizaClave(idUsuario, claveActual, nuevaClave);
                MessageUtil.sendSuccessModal("Actualización exitosa", "Se ha actualizado la clave correctamente.");
            } else {
                MessageUtil.sendErrorModal("Error validación", "Verifique que las claves coincidan.");
            }
        } catch (BusinessException ex) {
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
        }
    }

    private boolean camposValidos() {
        return StringUtils.isNotBlank(claveActual)
                && StringUtils.isNotBlank(nuevaClave)
                && StringUtils.isNotBlank(confirmacionNuevaClave)
                && Objects.equals(nuevaClave, confirmacionNuevaClave);
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getConfirmacionNuevaClave() {
        return confirmacionNuevaClave;
    }

    public void setConfirmacionNuevaClave(String confirmacionNuevaClave) {
        this.confirmacionNuevaClave = confirmacionNuevaClave;
    }

}
