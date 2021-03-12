/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import pyp.modelo.entidades.Insumo;
import pyp.DAO.IInsumoDAO;
import pyp.modelo.entidades.TipoInsumo;

/**
 *
 * @Gaes 5
 */
@Stateless
public class InsumoDAO extends AbstractDAO<Insumo> implements IInsumoDAO {

    public InsumoDAO() {
        super(Insumo.class);
    }
    
    @Override
    public List<Insumo> findByTipoInsumo(TipoInsumo tipoInsumoFiltro){
        TypedQuery<Insumo> query = getEntityManager().createNamedQuery("Insumo.findByTipoInsumo", Insumo.class);
        query.setParameter("tipoInsumoId", tipoInsumoFiltro.getId());
        return query.getResultList();
    }

}
