/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.Factura;
import pyp.DAO.IFacturaDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class FacturaDAO extends AbstractDAO<Factura> implements IFacturaDAO {

    public FacturaDAO() {
        super(Factura.class);
    }
    
}
