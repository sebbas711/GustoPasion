/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import java.util.List;
import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;

@Local
public interface IPedidosService {
    
    List<Estadopedido> getEstadoPedidosHabilitados() throws BusinessException;

    public List<Pedido> findPedidosByFilter(Estadopedido estadoPedidoFiltro) throws BusinessException;
    
}
