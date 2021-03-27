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
import pyp.DAO.ICategoriaProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;

/**
 *
 * @GAES 1
 */
@FacesConverter(forClass = CategoriaProducto.class)
public class CateogriaProductoConverter implements Converter{
    
    private ICategoriaProductoDAO categoriaProductoDAO;
    
    public CateogriaProductoConverter(){
        categoriaProductoDAO = CDI.current().select(ICategoriaProductoDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return categoriaProductoDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof CategoriaProducto) {
            CategoriaProducto categoriaProducto = (CategoriaProducto) obj;
            return categoriaProducto.getId().toString();

        }
        return null;
    }
    
}
