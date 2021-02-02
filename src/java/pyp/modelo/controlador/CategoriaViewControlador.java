/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.modelo.DAO.ICategoriaProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;

/**
 *
 * @author alejo
 */
@Named(value = "categoriaViewControlador")
@ViewScoped
public class CategoriaViewControlador implements Serializable{
    
    @EJB
    private ICategoriaProductoDAO cateDAO;
    
    private List<CategoriaProducto> categorias;

    /**
     * Creates a new instance of CategoriaViewControlador
     */
    public CategoriaViewControlador() {
    }
    
    @PostConstruct
    public void init(){
         categorias = cateDAO.findAll();
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
    
    
}
