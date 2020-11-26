/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.modelo.DAO.ITipopqrsDAO;
import pyp.modelo.entidades.Tipopqrs;

/**
 *
 * @author alejo
 */
@Named(value = "tipoPqrsView")
@ViewScoped
public class TipoPqrsView implements Serializable{

    /**
     * Creates a new instance of TipoPqrsView
     */
    public TipoPqrsView() {
    }
    
    private ITipopqrsDAO tipoPqrsDAO;
    
    private List<Tipopqrs> tiposPqrs;

    public List<Tipopqrs> getTiposPqrs() {
        if(tiposPqrs == null || tiposPqrs.isEmpty()){
            tiposPqrs = tipoPqrsDAO.findAll();
        }
        return tiposPqrs;
    }

    public void setTiposPqrs(List<Tipopqrs> tiposPqrs) {
        this.tiposPqrs = tiposPqrs;
    }
    
    
}