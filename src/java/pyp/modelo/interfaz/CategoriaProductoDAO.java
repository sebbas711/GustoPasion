/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.interfaz;

import pyp.modelo.DAO.ICategoriaProductoDAO;
import javax.ejb.Stateless;
import pyp.modelo.entidades.CategoriaProducto;

/**
 *
 * @author alejo
 */
@Stateless
public class CategoriaProductoDAO extends AbstractDAO<CategoriaProducto> implements ICategoriaProductoDAO {

    public CategoriaProductoDAO() {
        super(CategoriaProducto.class);
    }
    
}
