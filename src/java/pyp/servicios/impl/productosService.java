/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IProductoDAO;
import pyp.excepciones.BusinessException;
import pyp.excepciones.MessageException;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Producto;
import pyp.servicios.IProductosService;
/**
 *
 * @GAES 1
 */
@Stateless
public class productosService implements IProductosService {

    @EJB
    private ICategoriaProductoDAO categoriaProductoDao;
    @EJB
    private IProductoDAO productoDao;

    @Override
    public List<CategoriaProducto> getCategoriaProductoHabilitados() throws BusinessException {
        try {
            return categoriaProductoDao.findAll();
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_TIPO_INVENTARIO_ERROR, e);
        }
    }

    @Override
    public List<Producto> findProductosByFilter(CategoriaProducto categoriaProductoFiltro) throws BusinessException {
        try {
            if (Objects.isNull(categoriaProductoFiltro)) {
                return productoDao.findAll();
            }
            return productoDao.findByCategoriaProducto(categoriaProductoFiltro);
        } catch (Exception e) {
            throw new BusinessException(MessageException.BE_TIPO_INVENTARIO_ERROR, e);
        }
    }

}
