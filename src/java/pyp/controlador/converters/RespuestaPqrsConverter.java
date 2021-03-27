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
import pyp.DAO.IRespuestapqrsDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Respuestapqrs;

/**
 *
 * @GAES 1
 */
@FacesConverter(forClass = CategoriaProducto.class)
public class RespuestaPqrsConverter implements Converter{
    
    private IRespuestapqrsDAO respuestaPqrsDAO;
    
    public RespuestaPqrsConverter(){
        respuestaPqrsDAO = CDI.current().select(IRespuestapqrsDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return respuestaPqrsDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof Respuestapqrs) {
            Respuestapqrs respuestaPqrs = (Respuestapqrs) obj;
            return respuestaPqrs.getId().toString();

        }
        return null;
    }
    
}
