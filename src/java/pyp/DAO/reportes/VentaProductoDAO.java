/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.reportes;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pyp.DAO.impl.BaseDAO;
import pyp.modelo.dto.VentaProductoData;

/**
 *
 * @author PC
 */

@Stateless
public class VentaProductoDAO extends BaseDAO<VentaProductoData> implements IVentaProductoDAO {

    public VentaProductoDAO() {
        super(VentaProductoData.class);
    }

    
    @Override
    public List<VentaProductoData> getVentaPorProducto() {
        String queryStr = "select cat.nombre_categoria, sum(dp.cantidad) as cantidad_vidad from categoria_producto cat inner join producto pro on pro.categoria_producto = cat.id inner join detalle_pedido dp on dp.Producto = pro.id group by 1 order by 1 desc;";
        Query query = getEntityManager()
                .createNativeQuery(queryStr);
        List<Object[]> result = query.getResultList();
        List<VentaProductoData> ventas = new ArrayList<>();
        for(Object[] row: result){
            ventas.add(toVentaProductoData(row));
        }
        return ventas;
    }
    
        private VentaProductoData toVentaProductoData(Object[] row){
        VentaProductoData ventaProductoData = new VentaProductoData();
        ventaProductoData.setNombreCategoria((String) row[0]);
        ventaProductoData.setCantidadProductoVendida(Integer.valueOf(row[1].toString()));
        return ventaProductoData;
    }
}
