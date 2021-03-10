/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IInsumoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.servicios.IDescuentoInsumoService;

/**
 *
 * @author Gaes5
 */
@Stateless
public class DescuentoInsumoService implements IDescuentoInsumoService {

    @EJB
    private IInsumoDAO insumoDAO;

    @Override
    public void descontarDelInventario(List<DetallePedido> detallesPedido) throws BusinessException {
        validaDetallesPedido(detallesPedido);
        Map<Integer, Double> descuentos = getDescuentosPorInsumo(detallesPedido);
        Set<Integer> idsInsumos = descuentos.keySet();
        for(Integer id: idsInsumos){
            descontar(id, descuentos.get(id));
        }
    }
    
    private void validaDetallesPedido(List<DetallePedido> detallesPedido) throws BusinessException{
        if(Objects.isNull(detallesPedido)){
            throw new BusinessException(MessageException.BE_PEDIDO_VACIO);
        }
    }
    
    private Map<Integer,Double> getDescuentosPorInsumo(List<DetallePedido> detallesPedido){
        Map<Integer, Double> descuentos = new HashMap<>();
        for (DetallePedido detallePedido : detallesPedido) {
            for (InsumosDelProducto insumoProducto : detallePedido.getProducto().getInsumosDelProducto()) {
                int idInsumo = insumoProducto.getInsumo().getId();
                double descuento = insumoProducto.getCantidadInsumo() * detallePedido.getCantidad();
                descuento += descuentos.containsKey(idInsumo) ? descuentos.get(idInsumo) : 0;
                descuentos.put(idInsumo, descuento);
            }
        }
        return descuentos;
    }
    
    private void descontar(Integer id, Double descuento) throws BusinessException {
        Insumo insumo = insumoDAO.find(id);
        validaDescuentoInsumo(insumo, descuento);
        insumo.setCantidad(insumo.getCantidad() - descuento);
        insumoDAO.edit(insumo);
    }
    
    private void validaDescuentoInsumo(Insumo insumo, Double descuento) throws BusinessException{
        if(insumo.getCantidad()< descuento){
            throw new BusinessException(MessageException.BE_PEDIDO_INSUMO_BAJO);
        }
    }

}
