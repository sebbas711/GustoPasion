/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Pedido;
import pyp.DAO.IPedidoDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class PedidoDAO extends AbstractDAO<Pedido> implements IPedidoDAO {

    public PedidoDAO() {
        super(Pedido.class);
    }
    
}