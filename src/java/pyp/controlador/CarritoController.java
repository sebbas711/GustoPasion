/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pyp.modelo.entidades.Pedido;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author Ismael
 */
@Named
@SessionScoped
public class CarritoController implements Serializable {
    
    //private List<DetallePedido> detallesPedido;
    private Pedido pedido;
    private List<Producto> productosPedido;
    
    private Producto productoSeleccionado;
    private Integer cantidad;

    public CarritoController() {
    }
    
    @PostConstruct
    public void init(){
        //detallesPedido = new ArrayList<>();
        productosPedido = new ArrayList<>();
        
        
        pedido = new Pedido();
        //pedido.setDetallesPedido(productosPedido);
        //pedido.setDetallesPedido(detallesPedido);
    }
    
    public void agregarProducto(Producto producto){
        /*
        DetallePedido nuevoDetalle = new DetallePedido();
        nuevoDetalle.setPedido(pedido);
        nuevoDetalle.setProducto(productoSeleccionado);
        nuevoDetalle.cantidad(cantidad);
        detallesPedido.add(nuevoDetalle);
        */
        productosPedido.add(producto);
    }
    
    public void vaciar(){
        init();
    }

    public Pedido getPedido() {
        return pedido;
    }
    
    public Integer getCantidadItems(){
        return productosPedido.size();
    }

    public List<Producto> getProductosPedido() {
        return productosPedido;
    }
    
    
    
}
