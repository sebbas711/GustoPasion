/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pyp.controlador.sesion.SessionControlador;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.DetallePedido;
import pyp.modelo.entidades.Pedido;
import pyp.modelo.entidades.Producto;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.IPedidosService;
import pyp.util.MessageUtil;
import pyp.util.RedirectUtil;

/**
 *
 * @author Ismael
 */
@Named
@SessionScoped
public class CarritoController implements Serializable {

    private static final String TIPO_PEDIDO_DOMICILIO = "DOMICILIO";

    @Inject
    private SessionControlador sessionControlador;

    @EJB
    private IPedidosService pedidoService;

    private Pedido pedido;
    private List<DetallePedido> detallesPedido;

    public CarritoController() {
    }

    @PostConstruct
    public void init() {

        pedido = new Pedido();
        detallesPedido = new ArrayList<>();

        pedido.setDetallesPedido(detallesPedido);
        pedido.setTipoPedido(TIPO_PEDIDO_DOMICILIO);
    }

    private boolean setValoresDelUsuarioEnPedido() {
        Usuario usuario = sessionControlador.getUser();
        if (Objects.nonNull(usuario)) {
            pedido.setCliente(usuario.getCliente());
            pedido.setTelefono(Objects.nonNull(usuario.getTelefono()) ? Long.valueOf(usuario.getTelefono()).intValue() : 0);
            pedido.setPuntoEntrega(Objects.nonNull(usuario.getDireccion()) ? usuario.getDireccion() : " ");
            return true;
        } else {
            RedirectUtil.redirectTo("/app/Usuario/InicioSesion.xhtml");
            return false;
        }
    }

    void agregarProducto(Producto productoSeleccionado, int cantidadProducto) {
        DetallePedido nuevoDetalle = new DetallePedido();
        nuevoDetalle.setPedido(pedido);
        nuevoDetalle.setProducto(productoSeleccionado);
        nuevoDetalle.setCantidad(cantidadProducto);
        nuevoDetalle.setValorUnitario(productoSeleccionado.getPrecio());
        detallesPedido.add(nuevoDetalle);
    }

    public void realizarPedido() {
        try {
            if (setValoresDelUsuarioEnPedido()) {
                pedidoService.realizarPedido(pedido);
                vaciar();
                MessageUtil.sendInfo(null, "Pedido realizado", "Gracias por utilizar nuestro servicio de pedido a domicilio", Boolean.TRUE);
            }
        } catch (BusinessException be) {
            MessageUtil.sendBusinessException(null, be);
        }
    }

    public void btnCarroCompras() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (setValoresDelUsuarioEnPedido()) {
            ec.redirect(ec.getRequestContextPath() + "/app/ServicioAlCliente/compra.xhtml");
            vaciar();
        }
    }

    public void vaciar() {
        init();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Integer getCantidadItems() {
        return detallesPedido.size();
    }

}
