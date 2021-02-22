/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IUsuarioDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.*;


@Stateless
public class SessionService implements ISessionService {

    private static final int USUARIO_INACTIVO = 2;
    private static final int ID_ROL_ADMIN = 1;
    private static final int ID_ROL_CASHIER = 2;
    private static final int ID_ROL_AUXCO = 3;
    private static final int ID_ROL_OPER = 4;
    private static final int ID_ROL_CUSTOMER = 5;

    @EJB
    private IUsuarioDAO uDAO;

    @Override
    public Usuario login(String email, String password) throws BusinessException {
        Usuario user = uDAO.findByEmailAndPassword(email, password);
        if (!userExists(user)) {
            throw new BusinessException(MessageException.BE_USUARIO_NO_EXISTE);
        }
        if (userIsInavtive(user)) {
            throw new BusinessException(MessageException.BE_USUARIO_INACTIVO);
        }
        return user;
    }

    private boolean userIsInavtive(Usuario user) {
        return userExists(user) && USUARIO_INACTIVO == user.getEstado();
    }

    private boolean userExists(Usuario user) {
        return user != null;
    }

    @Override
    public boolean isAdmin(Usuario user) {
        return userHasRol(user, ID_ROL_ADMIN);
    }

    @Override
    public boolean isCashier(Usuario user) {
        return userHasRol(user, ID_ROL_CASHIER);
    }

    @Override
    public boolean isAuxCo(Usuario user) {
        return userHasRol(user, ID_ROL_AUXCO);
    }

    @Override
    public boolean isOperario(Usuario user) {
        return userHasRol(user, ID_ROL_OPER);
    }

    @Override
    public boolean isCustomer(Usuario user) {
        return userHasRol(user, ID_ROL_CUSTOMER);
    }

    private boolean userHasRoles(Usuario user) {
        return user.getRoles() != null && !user.getRoles().isEmpty();
    }

    private boolean userHasRol(Usuario user, int idRol) {
        boolean userExistAndHasRoles = userExists(user) && userHasRoles(user);
        if (userExistAndHasRoles) {
            boolean hasRol = false;
            for (Rol rol : user.getRoles()) {
                if (rol.getId() == idRol) {
                    hasRol = true;
                    break;
                }
            }
            //boolean hasRol = user.getRoles().stream().anyMatch(rol -> rol.getId() == ID_ROL_CUSTOMER);
            return userExistAndHasRoles && hasRol;
        }
        return userExistAndHasRoles;
    }

}
