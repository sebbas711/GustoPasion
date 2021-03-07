/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.DAO.IEstadopqrsDAO;
import pyp.modelo.entidades.Estadopqrs;

/**
 *
 * @author PC
 */
@Stateless
public class EstadopqrsDAO extends AbstractDAO<Estadopqrs> implements IEstadopqrsDAO {

    public EstadopqrsDAO() {
        super(Estadopqrs.class);
    }

    @Override
    public Estadopqrs findEstadoNueva() {
        return find(IEstadopqrsDAO.ID_ESTADO_NUEVA);
    }

    @Override
    public Estadopqrs findEstadoFinalizada() {
        return find(IEstadopqrsDAO.ID_ESTADO_FINALIZADA);
    }
    
}
