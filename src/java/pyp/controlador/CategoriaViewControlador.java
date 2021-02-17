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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author alejo
 */
@Named(value = "categoriaViewControlador")
@ViewScoped
public class CategoriaViewControlador implements Serializable{
    
    @EJB
    private ICategoriaProductoDAO cateDAO;
    
    @EJB
    private IProductoDAO producDAO;
    
    private List<CategoriaProducto> categorias;
    private CategoriaProducto seleccionCategoria = new CategoriaProducto();

    /**
     * Creates a new instance of CategoriaViewControlador
     */
    public CategoriaViewControlador() {
    }
    
    @PostConstruct
    public void init(){
         try {
            categorias = cateDAO.findAll();
            seleccionCategoria = cateDAO.find(1);
        } catch (Exception e) {
            System.out.println("pyp.modelo.controlador.CategoriaViewControlador()" + e.getMessage());
        }
    }
    
    public void categoriaSeleccionada(int id){
        try {
            seleccionCategoria = cateDAO.find(id);
        } catch (Exception e) {
            System.out.println("pyp.modelo.controlador.CategoriaViewControlador.categoriaSeleccionada()" + e.getMessage());
        }
    }
    
    public List<Producto> listaProductos(){
        try {
            return producDAO.listaProductosPorCategoria(seleccionCategoria.getId());
        } catch (Exception e) {
            System.out.println("pyp.modelo.controlador.CategoriaViewControlador.listaProductos()" + e.getMessage());
            return null;
        }
    }

    public List<CategoriaProducto> getCategorias() {
        if (categorias == null || categorias.isEmpty()) {
            categorias = cateDAO.findAll();
        }
        return categorias;
    }

    public void setCategorias(List<CategoriaProducto> categorias) {
        this.categorias = categorias;
    }

    public CategoriaProducto getSeleccionCategoria() {
        return seleccionCategoria;
    }

    public void setSeleccionCategoria(CategoriaProducto seleccionCategoria) {
        this.seleccionCategoria = seleccionCategoria;
    }
    
    
}
