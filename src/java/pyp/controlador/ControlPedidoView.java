/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;
import pyp.servicios.IPedidosService;
import pyp.util.MessageUtil;

@Named
@ViewScoped
public class ControlPedidoView implements Serializable {

    @EJB
    private IPedidosService pedidoService;

    private List<Estadopedido> estadoPedidosHabilitados;
    private List<Pedido> pedidos;
    private Estadopedido estadoPedidoFiltro;

    public ControlPedidoView() {
    }

    @PostConstruct
    public void init() {
        try {
            findPedidos();
            estadoPedidosHabilitados = pedidoService.getEstadoPedidosHabilitados();
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public String getColorByEstadoPedido(Estadopedido estadoPedido) {
        return "#" + (estadoPedido.getId()% 2 == 0 ? "951010" : "727272");
    }

    public void filtrarPorEstadoPedido(Estadopedido estadoPedidoFiltro) {
        this.estadoPedidoFiltro = estadoPedidoFiltro;
        findPedidos();
    }

    public void limpiarFiltroEstadoPedido() {
        this.estadoPedidoFiltro = null;
        findPedidos();
    }

    private void findPedidos() {
        try {
            pedidos = pedidoService.findPedidosByFilter(estadoPedidoFiltro);
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Estadopedido getEstadoPedidoFiltro() {
        return estadoPedidoFiltro;
    }

    public List<Estadopedido> getEstadoPedidosHabilitados() {
        return estadoPedidosHabilitados;
    }
    
    public void preparar(Pedido pedido) {
        try {
            pedidoService.prepararPedido(pedido.getId());
            MessageUtil.sendSuccessModal("Actualización exitosa", "Se ha iniciado la preparación del pedido.");
            init();
        } catch (BusinessException ex) {
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
        }

    }
    
    public boolean renderPreparar(Pedido pedido) {
        return pedido.getEstadoPedido().getId() == 1;
    }
    
    public void finalizarPreparacion(Pedido pedido) {
        try {
            pedidoService.terminarPreparacionPedido(pedido.getId());
            MessageUtil.sendSuccessModal("Actualización exitosa", "Se ha terminado la preparación del pedido.");
            init();
        } catch (BusinessException ex) {
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
        }

    }
    
    public boolean renderTerminaPreparacion(Pedido pedido) {
        return pedido.getEstadoPedido().getId() == 2;
    }

}
