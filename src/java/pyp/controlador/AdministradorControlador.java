/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.DAO.IAdministradorDAO;
import pyp.modelo.entidades.Administrador;

/**
 *
 * @GAES 1
 */
@Named(value = "administradorControlador")
@ViewScoped
public class AdministradorControlador implements Serializable{

    @EJB
    private IAdministradorDAO adminDAO;
    private List<Administrador> administradores;
    /**
     * Creates a new instance of AdministradorControlador
     */
    public AdministradorControlador() {
    }
    
    @PostConstruct
    public void init(){
        
    }

    public List<Administrador> getAdministradores() {
        if(administradores == null || administradores.isEmpty()){
            administradores = adminDAO.findAll();
        }
        return administradores;
    }
    
    
}
