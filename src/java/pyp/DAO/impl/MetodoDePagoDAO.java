/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.MetodoDePago;
import pyp.DAO.IMetodoDePagoDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class MetodoDePagoDAO extends AbstractDAO<MetodoDePago> implements IMetodoDePagoDAO {

    public MetodoDePagoDAO() {
        super(MetodoDePago.class);
    }
    
}
