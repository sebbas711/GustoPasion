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
import pyp.modelo.dto.PorcentajePQRS;

/**
 *
 * @GAES 1
 */
@Stateless
public class PqrsMesDAO extends BaseDAO<PorcentajePQRS> implements IPqrsMesDAO {

    public PqrsMesDAO() {
        super(PorcentajePQRS.class);
    }

    @Override
    public List<PorcentajePQRS> getPqrsPorMes() {
        String querySt = "select year(pq.fecha) as anio, month(pq.fecha) as mes, sum(pq.tipoPQRS) as total_mes from pqrs pq group by anio, mes order by anio desc, mes desc";
        Query query = getEntityManager()
                .createNativeQuery(querySt);
        List<Object[]> result = query.getResultList();
        List<PorcentajePQRS> pqr = new ArrayList<>();
        for (Object[] row : result) {
            pqr.add(toPorcentajePQRS(row));
        }

        return pqr;
    }

    private PorcentajePQRS toPorcentajePQRS(Object[] row) {
        PorcentajePQRS porcentajePQRS = new PorcentajePQRS();
        porcentajePQRS.setAnio((int) row[0]);
        porcentajePQRS.setMes((int) row[1]);
        porcentajePQRS.setTotalMes(Double.valueOf(row[2].toString()));
        return porcentajePQRS;
    }

}
