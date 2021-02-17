/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pyp.modelo.entidades.Producto;
import pyp.DAO.IProductoDAO;

/**
 *
 * @Gaes 5
 */
@Stateless
public class ProductoDAO extends AbstractDAO<Producto> implements IProductoDAO {

    public ProductoDAO() {
        super(Producto.class);
    }
    
    @Override
    public List<Producto> listaProductosPorCategoria(int fk_categoria){
        try {
            Query qt = em.createQuery("SELECT p FROM Producto p WHERE p.categoriaProducto.id = :fk_categoria");
            qt.setParameter("fk_categoria", fk_categoria);
            return qt.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
