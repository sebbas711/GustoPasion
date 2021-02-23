/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.modelo.entidades.CategoriaProducto;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class MenuControlador implements Serializable{
    
    public String getImageCagegoria(CategoriaProducto categoria){
        String urlImage = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/resource/img";
        if(Objects.isNull(categoria.getRutaImagen())){
            urlImage += "/categoria.jpg";
        } else {
            urlImage += categoria.getRutaImagen();
        }
        return urlImage;
    }
    
}
