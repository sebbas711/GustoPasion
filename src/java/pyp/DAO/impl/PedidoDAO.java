/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import pyp.modelo.entidades.Pedido;
import pyp.DAO.IPedidoDAO;
import pyp.modelo.entidades.Estadopedido;

/**
 *
 * @Gaes 5
 */
@Stateless
public class PedidoDAO extends AbstractDAO<Pedido> implements IPedidoDAO {

    public PedidoDAO() {
        super(Pedido.class);
    }
    
    @Override
    public List<Pedido> findByEstadoPedido(Estadopedido estadoPedidoFiltro){
        TypedQuery<Pedido> query = getEntityManager().createNamedQuery("Pedido.findByEstadoPedido",Pedido.class);
        query.setParameter("estadoPedidoId", estadoPedidoFiltro.getId());
        return query.getResultList();
    }
    
}
