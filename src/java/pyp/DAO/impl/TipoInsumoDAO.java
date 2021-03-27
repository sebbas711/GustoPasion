/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.TipoInsumo;
import pyp.DAO.ITipoInsumoDAO;

/**
 *
 * @GAES 1
 */
@Stateless
public class TipoInsumoDAO extends AbstractDAO<TipoInsumo> implements ITipoInsumoDAO {

    public TipoInsumoDAO() {
        super(TipoInsumo.class);
    }
    
}
