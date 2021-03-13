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
 * @author PC
 */
@Stateless
public class CantidadInsumosDAO extends BaseDAO<CantidadInsumo> implements ICantidadInsumoDAO {
    
    public CantidadInsumosDAO() {
        super(CantidadInsumo.class);
    }

    @Override
    public List<CantidadInsumo> getcantidadInsumo() {
        String queryStr = "select i.nombre, concat(monthname(i.fecha_ingreso), year(i.fecha_ingreso)) as mes, sum(inpro.cantidad_insumo) as total from insumo i inner join insumos_del_producto inpro on inpro.insumo = i.id group by 1,2 order by 2 asc;";
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
        cantidadInsumoData.setCantidadInsumo((int) row[0]);
        cantidadInsumoData.setNombreInsumo((String) row[1]);
        cantidadInsumoData.setMes((String) row[2]);
        return cantidadInsumoData;
    }

}
