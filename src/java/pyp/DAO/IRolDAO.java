/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import javax.ejb.Local;
import pyp.modelo.entidades.Rol;

/**
 *
 * @GAES 5
 */
@Local
public interface IRolDAO extends DAO<Rol> {
    
    public static final int ID_ROL_ADMIN = 1;
    public static final int ID_ROL_CAJERO = 2;
    public static final int ID_ROL_CLIENTE = 5;

    public Rol findRolCliente();



}
