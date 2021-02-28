/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pyp.modelo.entidades.Respuestapqrs;
import pyp.DAO.IRespuestapqrsDAO;

/**
 *
 * @author PC
 */
@Stateless
public class RespuestapqrsDAO extends AbstractDAO<Respuestapqrs> implements IRespuestapqrsDAO {

    public RespuestapqrsDAO() {
        super(Respuestapqrs.class);
    }
    
}
