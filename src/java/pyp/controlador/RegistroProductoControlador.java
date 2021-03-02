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
import pyp.DAO.IInsumoDAO;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.modelo.entidades.Producto;
import pyp.modelo.entidades.enums.EstadoPedidoEnum;
import pyp.util.MessageUtil;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class RegistroProductoControlador implements Serializable{
    
    @EJB
    private ICategoriaProductoDAO categoriaProductoDAO;
    @EJB
    private IInsumoDAO insumoDAO;
    @EJB
    private IProductoDAO productoDAO;
    
    private List<CategoriaProducto> categorias;
    private List<Insumo> insumos;
    private Producto producto;
    private InsumosDelProducto insumoProducto;
    
    @PostConstruct
    public void init(){
        this.producto = new Producto();
        this.producto.setInsumosDelProducto(new ArrayList<>());
        
        inicialializarInsumoProducto();
        
        categorias = categoriaProductoDAO.findAll();
        insumos = insumoDAO.findAll();
    }
    
    private void inicialializarInsumoProducto(){
        this.insumoProducto = new InsumosDelProducto();
        this.insumoProducto.setProducto(this.producto);
    }
    
    public void agregarInsumoAlProducto(){
        if(insumoProductoValido()){
            producto.getInsumosDelProducto().add(insumoProducto);
            inicialializarInsumoProducto();
        } else{
            MessageUtil.sendErrorModal("Error validación insumo", "Verifice la sección de insumos, todos los campos son obligaotrios");
        }
    }
    
    private boolean insumoProductoValido() {
        return Objects.nonNull(this.insumoProducto.getInsumo())
                && Objects.nonNull(insumoProducto.getCantidadInsumo()) 
                && insumoProducto.getCantidadInsumo() > 0;
    }
    
    public void registrarProducto(){
        if(productoValido()){
            productoDAO.create(producto);
            init();
            MessageUtil.sendSuccessModal("Registro exitoso", "Se ha registrado correctamente el producto con la respectiva parametrización para preparlo.");
        } else{
            MessageUtil.sendErrorModal("Error validación", "Verifice los datos ingresados");
        }
    }

    private boolean productoValido() {
        return Objects.nonNull(producto.getNombre()) && !producto.getNombre().isEmpty();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public List<CategoriaProducto> getCategorias() {
        return categorias;
    }
    
    public List<Insumo> getInsumos() {
        return insumos;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public InsumosDelProducto getInsumoProducto() {
        return insumoProducto;
    }
    
    public EstadoPedidoEnum[] getEstadosPedido(){
        return EstadoPedidoEnum.values();
    }
    //</editor-fold>
    
}
