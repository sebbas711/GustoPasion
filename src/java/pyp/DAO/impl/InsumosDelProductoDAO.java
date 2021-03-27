/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.DAO.IInsumosDelProductoDAO;

/**
 *
 * @GAES 1
 */
@Stateless
public class InsumosDelProductoDAO extends AbstractDAO<InsumosDelProducto> implements IInsumosDelProductoDAO {


    public InsumosDelProductoDAO() {
        super(InsumosDelProducto.class);
    }
    
}
