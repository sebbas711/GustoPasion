/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.ArrayList;
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
    
    private CategoriaProducto seleccionCategoria = new CategoriaProducto();
    private ArrayList<CategoriaProducto> categoriasList = new ArrayList<>();

    /**
     * Creates a new instance of CategoriaViewControlador
     */
    public CategoriaViewControlador() {
    }
    
    @PostConstruct
    public void init(){
        
    }

    public CategoriaProducto getSeleccionCategoria() {
        return seleccionCategoria;
    }

    public void setSeleccionCategoria(CategoriaProducto seleccionCategoria) {
        this.seleccionCategoria = seleccionCategoria;
    }

    public ArrayList<CategoriaProducto> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(ArrayList<CategoriaProducto> categoriasList) {
        this.categoriasList = categoriasList;
    }
    
    
    
}
