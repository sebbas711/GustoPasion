/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.pqrs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.IAdministradorDAO;
import pyp.DAO.IEstadopqrsDAO;
import pyp.DAO.IPqrsDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Pqrs;
import pyp.modelo.entidades.Respuestapqrs;
import pyp.servicios.pqrs.IRespondePqrsService;

/**
 *
 * @author Gaes5
 */
@Stateless
public class RespondePqrsService implements IRespondePqrsService {
    
    @EJB
    private IEstadopqrsDAO estadoPqrsDAO;
    @EJB
    private IAdministradorDAO administradorDAO;
    @EJB
    private IPqrsDAO pqrsDAO;

    @Override
    public void responder(Pqrs pqrs, Respuestapqrs respuesta) throws BusinessException {
        pqrsValida(pqrs);
        respuestaValida(respuesta);
        cambiarEstadoFinalizada(pqrs);
        completarInfoRespuesta(respuesta);
        darRespuesta(pqrs, respuesta);
    }

    private void pqrsValida(Pqrs pqrs) {
        //TODO
    }

    private void respuestaValida(Respuestapqrs respuesta) {
        //TODO
    }

    private void cambiarEstadoFinalizada(Pqrs pqrs) {
        pqrs.setEstadoPqrs(estadoPqrsDAO.findEstadoFinalizada());
    }

    private void completarInfoRespuesta(Respuestapqrs respuesta) {
        respuesta.setFecha(new Date());
    }

    private void darRespuesta(Pqrs pqrs, Respuestapqrs respuesta) throws BusinessException{
        try{
            if(sinRespuestas(pqrs)){
                pqrs.setRespuestapqrsList(new ArrayList<>());
            }
            pqrs.getRespuestapqrsList().add(respuesta);
            pqrsDAO.edit(pqrs);
        } catch(Exception e){
            
        }
    }
    
    private boolean sinRespuestas(Pqrs pqrs){
        return Objects.isNull(pqrs.getRespuestapqrsList()) || pqrs.getRespuestapqrsList().isEmpty();
    }
    
}
