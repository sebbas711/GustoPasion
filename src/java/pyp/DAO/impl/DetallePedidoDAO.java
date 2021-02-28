/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pyp.modelo.entidades.DetallePedido;
import pyp.DAO.IDetallePedidoDAO;

/**
 *
 * @author PC
 */
@Stateless
public class DetallePedidoDAO extends AbstractDAO<DetallePedido> implements IDetallePedidoDAO {

    public DetallePedidoDAO() {
        super(DetallePedido.class);
    }
    
}
