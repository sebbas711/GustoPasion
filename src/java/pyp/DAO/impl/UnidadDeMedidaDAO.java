/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pyp.modelo.entidades.UnidadDeMedida;
import pyp.DAO.IUnidadDeMedidaDAO;

/**
 *
 * @author PC
 */
@Stateless
public class UnidadDeMedidaDAO extends AbstractDAO<UnidadDeMedida> implements IUnidadDeMedidaDAO {


    public UnidadDeMedidaDAO() {
        super(UnidadDeMedida.class);
    }
    
}
