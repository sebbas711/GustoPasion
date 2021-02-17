/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.DAO.IProductoDAO;

/**
 *
 * @author alejo
 */
@Named(value = "productosViewControlador")
@ViewScoped
public class ProductosViewControlador implements Serializable{
    
    @EJB
    IProductoDAO productDAO;

    /**
     * Creates a new instance of ProductosViewControlador
     */
    public ProductosViewControlador() {
    }
    
    @PostConstruct
    public void init(){
    }
}
