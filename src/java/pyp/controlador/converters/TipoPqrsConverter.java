package pyp.controlador.converters;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pyp.DAO.ITipopqrsDAO;
import pyp.modelo.entidades.Tipopqrs;

/**
 *
 * @GAES 1
 */
@FacesConverter(forClass = Tipopqrs.class)
public class TipoPqrsConverter implements Converter{
    
    private ITipopqrsDAO tipoPqrsDAO;
    
    public TipoPqrsConverter() {
        tipoPqrsDAO = CDI.current().select(ITipopqrsDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
           try {
               Integer id = Integer.valueOf(value);
               return tipoPqrsDAO.find(id);
           } catch (NumberFormatException numberFormatException) {
           }
       }
       return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
      if(obj != null && obj instanceof Tipopqrs){
        Tipopqrs tipoPqrs = (Tipopqrs) obj;
        return tipoPqrs.getId().toString();
        }
        return "";
    }
    
}
