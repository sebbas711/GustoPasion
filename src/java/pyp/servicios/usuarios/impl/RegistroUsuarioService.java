/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.usuarios.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IRolDAO;
import pyp.DAO.IUsuarioDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Cliente;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.usuarios.IRegistroUsuarioService;

import pyp.modelo.entidades.Administrador;
import pyp.modelo.entidades.Cajero;

import static pyp.DAO.IRolDAO.ID_ROL_ADMIN;
import static pyp.DAO.IRolDAO.ID_ROL_CAJERO;
import static pyp.DAO.IRolDAO.ID_ROL_CLIENTE;

/**
 *
 * @author Gaes5
 */
@Stateless
public class RegistroUsuarioService implements IRegistroUsuarioService {

    private static final Short ID_ESTADO_ACTIVO = Short.valueOf("1");

    @EJB
    private IUsuarioDAO usuarioDAO;
    @EJB
    private IRolDAO rolDAO;

    @Override
    public Usuario registrarCliente(Usuario usuario) throws BusinessException {
        if (Objects.isNull(usuario)) {

        }
        usuario.setCliente(createCliente(usuario));
        usuario.setRoles(getRolesCliente());
        return registrar(usuario);
    }

    private Cliente createCliente(Usuario usuario) {
        Cliente cliente = new Cliente();
        cliente.setId(usuario.getId());
        cliente.setUsuario(usuario);
        return cliente;
    }

    private List<Rol> getRolesCliente() {
        return Arrays.asList(getRolCliente());
    }
    
    private Rol getRolCliente(){
        return rolDAO.findRolCliente();
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws BusinessException {
        if (Objects.isNull(usuario)) {
            
        }
        completarInformacionUsuario(usuario);
        return registrar(usuario);
    }
    
    private void completarInformacionUsuario(Usuario usuario){
        for(Rol rol: usuario.getRoles()){
            completarIformacionUsuarioPorRol(rol, usuario);
        }
    }

    private void completarIformacionUsuarioPorRol(Rol rol, Usuario usuario) throws AssertionError {
        switch (rol.getId()) {
            case ID_ROL_ADMIN:
                usuario.setAdministrador(new Administrador(usuario.getId()));break;
            case ID_ROL_CAJERO:
                usuario.setCajero(new Cajero(usuario.getId()));break;
            default:
                throw new AssertionError();
        }
    }

    private Usuario registrar(Usuario usuario) throws BusinessException {
        try {
            usuario.setEstado(ID_ESTADO_ACTIVO);
            usuarioDAO.create(usuario);
            return usuario;
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO, e);
        }
    }
}
