/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.pqrs.impl;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IAdministradorDAO;
import pyp.DAO.IEstadopqrsDAO;
import pyp.DAO.IPqrsDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Pqrs;
import pyp.servicios.pqrs.IRegistroPqrsService;

/**
 *
 * @GAES 1
 */
@Stateless
public class RegistroPqrsService implements IRegistroPqrsService {
    
    @EJB
    private IEstadopqrsDAO estadoPqrsDAO;
    @EJB
    private IAdministradorDAO administradorDAO;
    @EJB
    private IPqrsDAO pqrsDAO;

    @Override
    public void registroPqrs(Pqrs pqrs) throws BusinessException {
        pqrsValida(pqrs);
        completarInfoPqrs(pqrs);
        registrar(pqrs);
    }

    private void pqrsValida(Pqrs pqrs) {
        //TODO
    }

    private void completarInfoPqrs(Pqrs pqrs) {
        pqrs.setFecha(new Date());
        pqrs.setEstadoPqrs(estadoPqrsDAO.findEstadoNueva());
        pqrs.setAdministrador(administradorDAO.buscarAdminConMenosPqrs());
    }

    private void registrar(Pqrs pqrs) throws BusinessException{
        try{
            pqrsDAO.create(pqrs);
        } catch(Exception e){
            
        }
    }
    
}
