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
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author PC
 */
@Named
@SessionScoped
public class ProductoController implements Serializable{
    
    @EJB
    private IProductoDAO productoDAO;
    private List<Producto> productos;
    private Producto productoSeleccionado;
    
    
    private Producto producto;
    private List<InsumosDelProducto> insumoDelProducto;
    private Insumo insumoSeleccionado;
    private Integer cantidad;

    public ProductoController() {
    }
    
    @PostConstruct
    public void init(){
        insumoDelProducto = new ArrayList<>();
        
        producto = new Producto();
        producto.setInsumosDelProducto(insumoDelProducto);
    }
    
    public void agregarInsumo(InsumosDelProducto insumoProducto){
         InsumosDelProducto nuevoInsumoProducto = new InsumosDelProducto();
         nuevoInsumoProducto.setInsumo(insumoSeleccionado);
         nuevoInsumoProducto.setCantidadInsumo(cantidad);
         insumoDelProducto.add(insumoProducto);
    }

    public Producto getProducto() {
        return producto;
    }

    public List<InsumosDelProducto> getInsumoDelProducto() {
        return insumoDelProducto;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }
    
    public void seleccionarProducto(Producto p){
        System.out.println("Se ha seleccionado el usuario");
        System.out.println("p");
        this.productoSeleccionado = p;
    }
    
    
    
}
