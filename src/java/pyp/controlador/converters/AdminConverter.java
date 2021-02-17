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
import pyp.DAO.IAdministradorDAO;
import pyp.modelo.entidades.Administrador;

/**
 *
 * @author alejo
 */
@FacesConverter(forClass = Administrador.class)
public class AdminConverter implements Converter {

    private IAdministradorDAO auxDAO;
    
    public AdminConverter(){
        auxDAO = CDI.current().select(IAdministradorDAO.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return auxDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
       if (obj != null && obj instanceof Administrador) {
            Administrador adm = (Administrador) obj;
            return adm.getId().toString();

        }
        return null;
    }
    
}
