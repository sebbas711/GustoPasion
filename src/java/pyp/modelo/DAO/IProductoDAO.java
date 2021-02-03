/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Producto;

/**
 *
 * @GAES 5
 */
@Local
public interface IProductoDAO extends DAO<Producto> {
    
    public List<Producto> listaProductosPorCategoria(int fk_categoria);
}
