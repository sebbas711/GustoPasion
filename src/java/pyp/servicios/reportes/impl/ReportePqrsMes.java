/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.reportes.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.reportes.IPqrsMesDAO;
import pyp.modelo.dto.PorcentajePQRS;
import pyp.servicios.reportes.IReportePqrsMes;

/**
 *
 * @author alejo
 */
@Stateless
public class ReportePqrsMes implements IReportePqrsMes {

    @EJB
    private IPqrsMesDAO pqrsMesDAO;

    @Override
    public List<PorcentajePQRS> get() {
        return pqrsMesDAO.getPqrsPorMes();
    }

}
