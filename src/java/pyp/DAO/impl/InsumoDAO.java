/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Insumo;
import pyp.DAO.IInsumoDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class InsumoDAO extends AbstractDAO<Insumo> implements IInsumoDAO {

    public InsumoDAO() {
        super(Insumo.class);
    }
    
}