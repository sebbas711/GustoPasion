/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IEstadopedidoDAO;
import pyp.DAO.IPedidoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;
import pyp.servicios.IDescuentoInsumoService;
import pyp.servicios.IPedidosService;

@Stateless
public class PedidosService implements IPedidosService {

    @EJB
    private IEstadopedidoDAO estadoPedidoDao;
    @EJB
    private IPedidoDAO pedidoDao;
    @EJB
    private IDescuentoInsumoService descuentoInsumoService;

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

    @Override
    public void realizarPedido(Pedido pedido) throws BusinessException {
        validaPedidoNoEstaVacio(pedido);
        validaDetallePedidoNoEstaVacio(pedido);
        validaDetallesPedido(pedido.getDetallesPedido());
        registrarPedido(pedido);
        descuentoInsumoService.descontarDelInventario(pedido.getDetallesPedido());
    }

    private void validaPedidoNoEstaVacio(Pedido pedido) throws BusinessException {
        if (Objects.isNull(pedido)) {
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO);
        }
    }

    private void validaDetallePedidoNoEstaVacio(Pedido pedido) throws BusinessException {
        if (Objects.isNull(pedido.getDetallesPedido()) && !pedido.getDetallesPedido().isEmpty()) {
            throw new BusinessException(MessageException.BE_PEDIDO_SIN_PRODUCTOS);
        }
    }
    
    private void validaDetallesPedido(List<DetallePedido> detallesPedido) throws BusinessException{
        for(DetallePedido detallePedido: detallesPedido){
            validaCantidadDetallePedido(detallePedido);
            validaValorUnitarioDetallePedido(detallePedido);
        }
    }

    private void validaCantidadDetallePedido(DetallePedido detallePedido) throws BusinessException {
        if (Objects.isNull(detallePedido.getCantidad()) || detallePedido.getCantidad() <= 0) {
            throw new BusinessException(MessageException.BE_PEDIDO_SIN_CANTIDAD, detallePedido.getProducto().getNombre());
        }
    }
    
    private void validaValorUnitarioDetallePedido(DetallePedido detallePedido) throws BusinessException {
        if (Objects.isNull(detallePedido.getValorUnitario()) || detallePedido.getValorUnitario() <= 0) {
            throw new BusinessException(MessageException.BE_PEDIDO_SIN_VALOR_UNITARIO, detallePedido.getProducto().getNombre());
        }
    }

    private void registrarPedido(Pedido pedido) throws BusinessException {
        try {
            pedido.setFecha(new Date());
            pedido.setEstadoPedido(estadoPedidoDao.findEstadoSolicitado());
            pedidoDao.create(pedido);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ERROR_REGISTRAR_PEDIDO, e);
        }
    }

}
