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
import pyp.modelo.dto.VentaMesData;

/**
 *
 * @author Gaes5
 */
@Stateless
public class VentaMesDAO extends BaseDAO<VentaMesData> implements IVentaMesDAO {

    public VentaMesDAO() {
        super(VentaMesData.class);
    }
    
    /*
    @SqlResultSetMapping(
        name = "VentaMesDataMapping",
        classes = @ConstructorResult(
                targetClass = VentaMesData.class,
                columns = {@ColumnResult(name = "anio"), 
                            @ColumnResult(name = "mes"), 
                            @ColumnResult(name = "totalMes")}))
    */

    @Override
    public List<VentaMesData> getVentasPorMes() {
        String queryStr = "select year(p.fecha) as anio, month(p.fecha) as mes, sum(p.valor_total) as total_mes from pedido p group by anio, mes order by anio desc, mes desc";
        Query query = getEntityManager()
                .createNativeQuery(queryStr);
        List<Object[]> result = query.getResultList();
        List<VentaMesData> ventas = new ArrayList<>();
        for(Object[] row: result){
            ventas.add(toVentaMesData(row));
        }
        
        return ventas;
    }
    
    private VentaMesData toVentaMesData(Object[] row){
        VentaMesData ventaMesData = new VentaMesData();
        ventaMesData.setAnio((int) row[0]);
        ventaMesData.setMes((int) row[1]);
        ventaMesData.setTotalMes((double) row[2]);
        return ventaMesData;
    }

}
