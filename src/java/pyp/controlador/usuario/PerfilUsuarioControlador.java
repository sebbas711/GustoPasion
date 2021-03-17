/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador.usuario;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
public class PerfilUsuarioControlador implements Serializable {

    @Inject
    private SessionControlador sessionControlador;
    
    @EJB
    private IConsultaUsuarioService consultaUsuarioService;
    @EJB
    private IActualizacionUsuarioService actualizacionUsuarioService;
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        try {
            Integer idUsuario = sessionControlador.getUser().getId();
            usuario = consultaUsuarioService.consultarPorId(idUsuario);
        } catch (BusinessException ex) {
            //TODO
        } catch(Exception e){
            //TODO
        }
    }
    
    public void actualizar(){
        try {
            if (usuario != null) {
                actualizacionUsuarioService.actualizaDatosBasicos(usuario);
                sessionControlador.setUser(usuario);
                MessageUtil.sendSuccessModal("Actualización exitosa", "Se han actualizado correctamente los datos del usuario.");
            }
        } catch (BusinessException e) {
            MessageUtil.sendErrorModal(e.getMessage(), e.getDetails());
        } catch (Exception e) {
            MessageUtil.sendErrorModal("Error de actualización", "No se pudo actualizar el usuario");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
}
