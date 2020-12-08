/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.converters;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pyp.modelo.DAO.IPqrsDAO;
import pyp.modelo.entidades.Pqrs;

/**
 *
 * @author alejo
 */
@FacesConverter(forClass = Pqrs.class)
public class PqrsConverter implements Converter{
    
    private IPqrsDAO pqDAO;
    
    public PqrsConverter(){
        pqDAO = CDI.current().select(IPqrsDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return pqDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof Pqrs) {
            Pqrs pq = (Pqrs) obj;
            return pq.getId().toString();

        }
        return null;
    }
    
}
