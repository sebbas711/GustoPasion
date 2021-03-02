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
import pyp.modelo.entidades.Insumo;

/**
 *
 * @author alejo
 */
@FacesConverter(forClass = Insumo.class)
public class InsumoConverter implements Converter{
    
    private IInsumoDAO insumoDAO;
    
    public InsumoConverter(){
        insumoDAO = CDI.current().select(IInsumoDAO.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            try {
                Integer id = Integer.valueOf(value);
                return insumoDAO.find(id);
            }catch (NumberFormatException numberFormatException){
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        if (obj != null && obj instanceof Insumo) {
            Insumo insumo = (Insumo) obj;
            return insumo.getId().toString();

        }
        return null;
    }
    
}
