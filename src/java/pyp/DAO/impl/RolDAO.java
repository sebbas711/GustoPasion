/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Rol;
import pyp.DAO.IRolDAO;

import static pyp.DAO.IRolDAO.ID_ROL_CLIENTE;

/**
 *
 * @Gaes 5
 */
@Stateless
public class RolDAO extends AbstractDAO<Rol> implements IRolDAO {

    public RolDAO() {
        super(Rol.class);
    }

    @Override
    public Rol findRolCliente() {
        return getEntityManager()
                .find(entityClass, ID_ROL_CLIENTE);
    }

}
