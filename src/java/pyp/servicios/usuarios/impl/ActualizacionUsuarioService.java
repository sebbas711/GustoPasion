/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.usuarios.impl;

import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IUsuarioDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.usuarios.IActualizacionUsuarioService;
import pyp.servicios.usuarios.IConsultaUsuarioService;

/**
 *
 * @author Gaes5
 */
@Stateless
public class ActualizacionUsuarioService implements IActualizacionUsuarioService {
    
    @EJB
    private IUsuarioDAO usuarioDAO;

    @Override
    public void actualizaDatosBasicos(Usuario usuario) throws BusinessException {
        if(Objects.isNull(usuario)){
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO);
        }
        //TODO Agregar validaciones
        usuarioDAO.edit(usuario);
    }

    @Override
    public void actualizaClave(Integer idUsuario, String claveAnterior, String claveNueva) throws BusinessException {
        Usuario usuario = getUsuario(idUsuario, claveAnterior);
        usuario.setContraseña(claveNueva);
        usuarioDAO.edit(usuario);
    }

    private Usuario getUsuario(Integer idUsuario, String claveAnterior) throws BusinessException {
        Usuario usuario = usuarioDAO.findByIdAndPassword(idUsuario, claveAnterior);
        if(Objects.isNull(usuario)){
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO);//Clave actual no coincide
        }
        return usuario;
    }


}
