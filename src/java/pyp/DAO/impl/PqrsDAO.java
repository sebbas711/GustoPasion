/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import pyp.modelo.entidades.Pqrs;
import pyp.DAO.IPqrsDAO;
import pyp.modelo.entidades.Cliente;
import pyp.modelo.entidades.Estadopqrs;

/**
 *
 * @Gaes 5
 */
@Stateless
public class PqrsDAO extends AbstractDAO<Pqrs> implements IPqrsDAO {

    public PqrsDAO() {
        super(Pqrs.class);
    }
    
    @Override
    public List<Pqrs> findByEstadoPqrs(Estadopqrs estadoPqrsFiltro){
        TypedQuery<Pqrs> query = getEntityManager().createNamedQuery("Pqrs.findByEstadoPqrs", Pqrs.class);
        query.setParameter("estadoPqrsId", estadoPqrsFiltro.getId());
        return query.getResultList();
    }

   /* @Override
    public List<Pqrs> finByCustomer(Cliente customer) {
        TypedQuery<Pqrs> query = getEntityManager().createNamedQuery("Pqrs.findByUser", Pqrs.class);
        query.setParameter("userId", customer.getId());
        return query.getResultList();
    }*/

    
    }

