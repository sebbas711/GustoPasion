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
import pyp.servicios.usuarios.IConsultaUsuarioService;

/**
 *
 * @author Gaes5
 */
@Stateless
public class ConsultaUsuarioService implements IConsultaUsuarioService {
    
    @EJB
    private IUsuarioDAO usuarioDAO;

    @Override
    public Usuario consultarPorId(Integer idUsuario) throws BusinessException {
        if(Objects.isNull(idUsuario)){
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO);
        }
        return usuarioDAO.find(idUsuario);
    }

}
