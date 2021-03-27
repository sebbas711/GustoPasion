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
import javax.inject.Inject;
import javax.inject.Named;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IProductoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Estadopedido;
import pyp.modelo.entidades.Pedido;
import pyp.modelo.entidades.Producto;
import pyp.modelo.entidades.enums.EstadoPedidoEnum;
import pyp.servicios.IPedidosService;
import pyp.util.MessageUtil;

/**
 *
 * @GAES 1
 */
@Named
@ViewScoped
public class RegistroPedidoControlador implements Serializable {

    @Inject
    private BuscarClienteControlador buscarClienteControlador;

    @EJB
    private ICategoriaProductoDAO categoriaProductoDAO;

    @EJB
    private IProductoDAO productoDAO;

    @EJB
    private IPedidosService pedidosService;

    private List<CategoriaProducto> categorias;
    private List<Estadopedido> estados;
    private List<Producto> productos;
    private Pedido pedido;
    private DetallePedido detallePedido;

    @PostConstruct
    public void init() {
        this.pedido = new Pedido();
        this.pedido.setDetallesPedido(new ArrayList<>());

        inicialializarPedidoProducto();

        categorias = categoriaProductoDAO.findAll();
        productos = productoDAO.findAll();
    }

    private void inicialializarPedidoProducto() {
        this.detallePedido = new DetallePedido();
        this.detallePedido.setPedido(pedido);
    }

    public void agregarProductoAlPedido() {
        if (detallePedidoValido()) {
            detallePedido.setValorUnitario(detallePedido.getProducto().getPrecio());
            pedido.getDetallesPedido().add(detallePedido);
            pedido.calcularTotales();
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

    public void registrarPedido() throws BusinessException {
        try {
            pedido.setCliente(buscarClienteControlador.getCliente());
            pedidosService.realizarPedido(pedido);
            limpiarForm();
            MessageUtil.sendInfoModal("Pedido Realizado", "Registro exitoso, este atento al proceso del pedido");
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_ERROR_REGISTRAR_PEDIDO, e);
        }
    }

    private void limpiarForm() {
        init();
        buscarClienteControlador.limpiar();
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

    public Pedido getPedido() {
        return pedido;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public EstadoPedidoEnum[] getEstadosPedido() {
        return EstadoPedidoEnum.values();
    }

    //</editor-fold>
}
