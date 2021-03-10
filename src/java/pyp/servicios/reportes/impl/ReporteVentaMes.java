/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.reportes.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.reportes.IVentaMesDAO;
import pyp.modelo.dto.VentaMesData;
import pyp.servicios.reportes.IReporteVentaMesService;

/**
 *
 * @author Ismael
 */
@Stateless
public class ReporteVentaMes implements IReporteVentaMesService{
    
    @EJB
    private IVentaMesDAO ventaMesDAO;

    @Override
    public List<VentaMesData> get() {
        return ventaMesDAO.getVentasPorMes();
    }
    
}
