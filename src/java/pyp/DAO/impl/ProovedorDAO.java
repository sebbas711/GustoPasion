/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Proovedor;
import pyp.DAO.IProovedorDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class ProovedorDAO extends AbstractDAO<Proovedor> implements IProovedorDAO {

    public ProovedorDAO() {
        super(Proovedor.class);
    }
    
}
