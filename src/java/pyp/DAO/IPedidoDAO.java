/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;

/**
 *
 * @GAES 5
 */
@Local
public interface IPedidoDAO extends DAO<Pedido> {

    List<Pedido> findByEstadoPedido(Estadopedido estadoPedidoFiltro);
    
    void updateState(Integer id, Estadopedido state) throws Exception;
    
}
