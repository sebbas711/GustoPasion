/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IEstadopedidoDAO;
import pyp.DAO.IPedidoDAO;
import pyp.DAO.IPedidoDAO;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;
import pyp.modelo.entidades.Producto;
import pyp.modelo.entidades.enums.EstadoPedidoEnum;
import pyp.util.MessageUtil;

@Named
@ViewScoped
public class RegistroPedidoControlador implements Serializable {

    @EJB
    private ICategoriaProductoDAO categoriaProductoDAO;

    @EJB
    private IEstadopedidoDAO estadoPedidoDAO;

    @EJB
    private IProductoDAO productoDAO;

    @EJB
    private IPedidoDAO pedidoDAO;

    private List<CategoriaProducto> categorias;
    private List<Estadopedido> estados;
    private List<Producto> productos;
    private Pedido pedidos;
    private DetallePedido detallePedido;

    @PostConstruct
    public void init() {
        this.pedidos = new Pedido();
        this.pedidos.setDetallesPedido(new ArrayList<>());

        inicialializarPedidoProducto();

        categorias = categoriaProductoDAO.findAll();
        productos = productoDAO.findAll();
    }

    private void inicialializarPedidoProducto() {
        this.detallePedido = new DetallePedido();
        this.detallePedido.setPedido(pedidos);
    }

    public void agregarProductoAlPedido() {
        if (detallePedidoValido()) {
            pedidos.getDetallesPedido().add(detallePedido);
            inicialializarPedidoProducto();
        } else {
            MessageUtil.sendErrorModal("Error validación insumo", "Verifice la sección de pedidos, todos los campos son obligaotrios");
        }
    }

    private boolean detallePedidoValido() {
        return Objects.nonNull(this.detallePedido.getProducto())
                && Objects.nonNull(detallePedido.getCantidad())
                && detallePedido.getCantidad() > 0;
    }

    public void registrarPedido() {
        pedidoDAO.create(pedidos);
        init();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public List<CategoriaProducto> getCategorias() {
        return categorias;
    }

    public List<Estadopedido> getEstados() {
        return estados;
    }
    

    public List<Producto> getProductos() {
        return productos;
    }

    public Pedido getPedidos() {
        return pedidos;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public EstadoPedidoEnum[] getEstadosPedido() {
        return EstadoPedidoEnum.values();
    }
    
    //</editor-fold>

}
