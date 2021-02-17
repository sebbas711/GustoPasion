/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;
import pyp.modelo.entidades.Administrador;
import pyp.DAO.IAdministradorDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class AdministradorDAO extends AbstractDAO<Administrador> implements IAdministradorDAO {

    public AdministradorDAO() {
        super(Administrador.class);
    }

    @Override
    public Administrador buscarAdminConMenosPqrs() {
        Query q = em.createNativeQuery("select a.* from administrador a " 
                + "left join pqrs pq on pq.administrador = a.id"
                + "group by a.id"
                + "order by count(pq.id) asc "
                + "limit 1", Administrador.class);
        return (Administrador) q.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
    
}
