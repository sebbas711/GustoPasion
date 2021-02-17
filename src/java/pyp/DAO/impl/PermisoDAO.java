/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Permiso;
import pyp.DAO.IPermisoDAO;

/**
 *
 * @author alejo
 */
@Stateless
public class PermisoDAO extends AbstractDAO<Permiso> implements IPermisoDAO {

    public PermisoDAO() {
        super(Permiso.class);
    }
    
}
