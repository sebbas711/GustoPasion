/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author Gaes5
 */
@Named
@ViewScoped
public class MenuControlador implements Serializable {

    public static final int CANTIDAD_MINIMA = 1;

    @Inject
    private CarritoController carritoController;

    @EJB
    private ICategoriaProductoDAO categoriaDAO;

    private List<CategoriaProducto> categorias;
    private CategoriaProducto categoriaSeleccionada;
    private Producto productoSeleccionado;
    private int cantidadProducto;

    @PostConstruct
    public void init() {
        inicializarAgregarAlCarrito();
    }

    public void inicializarAgregarAlCarrito() {
        productoSeleccionado = null;
        cantidadProducto = CANTIDAD_MINIMA;
    }

    public String getImageCagegoria(CategoriaProducto categoria) {
        String urlImage = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/resource/img";
        if (Objects.isNull(categoria.getRutaImagen())) {
            urlImage += "/categoria.jpg";
        } else {
            urlImage += categoria.getRutaImagen();
        }
        return urlImage;
    }

    public void deseleccionarCategoria() {
        categoriaSeleccionada = null;
    }

    public void deseleccionarProducto() {
        inicializarAgregarAlCarrito();
    }

    public void agregarProductoAlCarrito() {
        carritoController.agregarProducto(productoSeleccionado, cantidadProducto);
        inicializarAgregarAlCarrito();
    }

    public boolean renderCategorias() {
        return Objects.isNull(categoriaSeleccionada);
    }

    public boolean renderProductosCategoria() {
        return Objects.nonNull(categoriaSeleccionada) && Objects.isNull(productoSeleccionado);
    }

    public boolean renderAgregarProducto() {
        return Objects.nonNull(categoriaSeleccionada) && Objects.nonNull(productoSeleccionado);
    }

    public void incrementarCantidadProducto() {
        cantidadProducto++;
    }

    public void disminuirCantidadProducto() {
        if (cantidadProducto > CANTIDAD_MINIMA) {
            cantidadProducto--;
        }
    }
    
    public double getSubtotalAgregarCarrito(){
        return productoSeleccionado.getPrecio() * cantidadProducto;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public List<CategoriaProducto> getCategorias() {
        if (Objects.isNull(categorias)) {
            categorias = categoriaDAO.findAll();
        }
        return categorias;
    }

    public CategoriaProducto getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void seleccionarCategoria(CategoriaProducto categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void seleccionarProducto(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    //</editor-fold>
}
