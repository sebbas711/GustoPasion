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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import pyp.DAO.IProductoDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Producto;
import pyp.servicios.IProductosService;
import pyp.util.MessageUtil;

/**
 *
 * @GAES 1
 */
@Named
@ViewScoped
public class ControlProductosView implements Serializable {

    @EJB
    private IProductosService productosService;

    private List<CategoriaProducto> categoriaProductosHabilitados;
    private List<Producto> productos;
    private CategoriaProducto categoriaProductoFiltro;

    @EJB
    private IProductoDAO productoDAO;
    private Producto productoSeleccionado;

    public ControlProductosView() {
    }

    @PostConstruct
    public void init() {
        try {
            findProductos();
            categoriaProductosHabilitados = productosService.getCategoriaProductoHabilitados();
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public String getColorByCategoriaProducto(CategoriaProducto categoriaProducto) {
        return "#" + (categoriaProducto.getId() % 2 == 0 ? "951010" : "727272");
    }
    
    public void filtrarPorCategoriaProducto(CategoriaProducto categoriaProductoFiltro) {
        this.categoriaProductoFiltro = categoriaProductoFiltro;
        findProductos();
    }

    public void limpiarFiltroCategoriaProducto() {
        this.categoriaProductoFiltro = null;
        findProductos();
    }

    private void findProductos() {
        try {
            productos = productosService.findProductosByFilter(categoriaProductoFiltro);
        } catch (BusinessException ex) {
            MessageUtil.sendBusinessException(null, ex);
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<CategoriaProducto> getCategoriaProductosHabilitados() {
        return categoriaProductosHabilitados;
    }

    public CategoriaProducto getCategoriaProductoFiltro() {
        return categoriaProductoFiltro;
    }
    

    public void seleccionarProducto(Producto pro) {
        System.out.println("Se ha seleccionado el usuario");
        System.out.println(pro);
        this.productoSeleccionado = pro;
    }

    public void eliminar() {
        String mensajeRequest = "";
        try {
            productoDAO.remove(productoSeleccionado);
            mensajeRequest = "swal('Producto Eliminado', 'Correctamente', 'success');";
            productos = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar el Producto', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }
    
        public void actualizar() {
        String mensajeRequest = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (productoSeleccionado != null) {
                productoDAO.edit(productoSeleccionado);
                mensajeRequest = "swal('Actulizado', 'Correctamente', 'success');";
                productos = null;
            }
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo actualizar el producto', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);

    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

}
