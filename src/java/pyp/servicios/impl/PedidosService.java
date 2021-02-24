/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IEstadopedidoDAO;
import pyp.DAO.IPedidoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;
import pyp.servicios.IPedidosService;

@Stateless
public class PedidosService implements IPedidosService {

    @EJB
    private IEstadopedidoDAO estadoPedidoDao;
    @EJB
    private IPedidoDAO pedidoDao;

    @Override
    public List<Estadopedido> getEstadoPedidosHabilitados() throws BusinessException {
        try {
            return estadoPedidoDao.findAll();
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ESTADO_PEDIDO_ERROR, e);
        }
    }

    @Override
    public List<Pedido> findPedidosByFilter(Estadopedido estadoPedidoFiltro) throws BusinessException {
        try {
            if (Objects.isNull(estadoPedidoFiltro)) {
                return pedidoDao.findAll();
            }
            return pedidoDao.findByEstadoPedido(estadoPedidoFiltro);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ESTADO_PEDIDO_ERROR, e);
        }

    }

}
