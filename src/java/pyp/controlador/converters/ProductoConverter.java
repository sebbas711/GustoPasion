/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador.converters;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pyp.DAO.IInsumoDAO;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author alejo
 */
@FacesConverter(forClass = Producto.class)
public class ProductoConverter implements Converter{
    
    private IProductoDAO productoDAO;
    
    public ProductoConverter(){
        productoDAO = CDI.current().select(IProductoDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return productoDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof Producto) {
            Producto producto = (Producto) obj;
            return producto.getId().toString();

        }
        return null;
    }
    
}
