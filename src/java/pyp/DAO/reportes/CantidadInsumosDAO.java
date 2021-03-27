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
import pyp.DAO.impl.BaseDAO;
import pyp.modelo.dto.CantidadInsumo;

/**
 *
 * @GAES 1
 */
@Stateless
public class CantidadInsumosDAO extends BaseDAO<CantidadInsumo> implements ICantidadInsumoDAO {
    
    public CantidadInsumosDAO() {
        super(CantidadInsumo.class);
    }

    @Override
    public List<CantidadInsumo> getcantidadInsumo() {
        String queryStr = getStringSQL();
        Query query = getEntityManager()
                .createNativeQuery(queryStr);
        List<Object[]> result = query.getResultList();
        List<CantidadInsumo> cantidad = new ArrayList<>();
        for (Object[] row : result) {
            cantidad.add(toCantidadInsumo(row));
        }

        return cantidad;
    }

    private CantidadInsumo toCantidadInsumo(Object[] row) {
        CantidadInsumo cantidadInsumoData = new CantidadInsumo();
        cantidadInsumoData.setMes((String) row[0]);
        cantidadInsumoData.setNombreInsumo((String) row[1]);
        cantidadInsumoData.setCantidadInsumo(Double.valueOf(row[2].toString()));
        return cantidadInsumoData;
    }

    private String getStringSQL() {
        return "select concat(monthname(info.fecha_pedido), year(info.fecha_pedido)) as mes, info.nombre_insumo, info.cantidad_por_pedido "
                + "from("
                + "     select i.id as id_insumo, i.nombre as nombre_insumo, ped.fecha as fecha_pedido, dp.id as id_detalle_pedido, (dp.cantidad * inpro.cantidad_insumo) as cantidad_por_pedido "
                + "     from insumo i "
                + "     inner join insumos_del_producto inpro on inpro.insumo = i.id "
                + "     inner join detalle_pedido dp on dp.Producto = inpro.Producto "
                + "     inner join pedido ped on ped.id = dp.Pedido "
                + ") info "
                + "group by 1,2 "
                + "order by 2 asc;";
    }

}
