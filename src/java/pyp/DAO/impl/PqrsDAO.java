/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Pqrs;
import pyp.DAO.IPqrsDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class PqrsDAO extends AbstractDAO<Pqrs> implements IPqrsDAO {

    public PqrsDAO() {
        super(Pqrs.class);
    }
    
}
