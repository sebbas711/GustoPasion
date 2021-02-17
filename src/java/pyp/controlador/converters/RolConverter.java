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
import pyp.DAO.IRolDAO;
import pyp.modelo.entidades.Rol;

/**
 *
 * @author alejo
 */
@FacesConverter(forClass = Rol.class)
public class RolConverter implements Converter{
    
    private IRolDAO rDAO;
    
    public RolConverter(){
        rDAO = CDI.current().select(IRolDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return rDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof Rol) {
            Rol rol = (Rol) obj;
            return rol.getId().toString();

        }
        return null;
    }
    
}
