/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import java.util.List;
import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Producto;

@Local
public interface IProductosService {
    
    List<CategoriaProducto> getCategoriaProductoHabilitados() throws BusinessException;

    public List<Producto> findProductosByFilter(CategoriaProducto categoriaProductoFiltro) throws BusinessException;
    
}
