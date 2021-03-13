/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.reportes.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.reportes.ICantidadInsumoDAO;
import pyp.modelo.dto.CantidadInsumo;
import pyp.servicios.reportes.IReporteCantidadInsumo;

/**
 *
 * @author PC
 */
@Stateless
public class ReporteCantidadInsumo implements IReporteCantidadInsumo {

    @EJB
    private ICantidadInsumoDAO cantidadInsumoDAO;

    @Override
    public List<CantidadInsumo> get() {
        return cantidadInsumoDAO.getcantidadInsumo();

    }
}
