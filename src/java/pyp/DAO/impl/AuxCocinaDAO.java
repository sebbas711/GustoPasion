/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import javax.ejb.Stateless;
import pyp.modelo.entidades.AuxCocina;
import pyp.DAO.IAuxCocinaDAO;

/**
 *
 * @GAES 1
 */
@Stateless
public class AuxCocinaDAO extends AbstractDAO<AuxCocina> implements IAuxCocinaDAO {

    public AuxCocinaDAO() {
        super(AuxCocina.class);
    }
    
}
