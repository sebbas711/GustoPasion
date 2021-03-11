/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.reportes.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import pyp.DAO.reportes.IVentaProductoDAO;
import pyp.modelo.dto.VentaProductoData;
import pyp.servicios.reportes.IReporteVentaProductoService;

/**
 *
 * @author PC
 */
@Stateless
public class ReporteVentaPorducto implements IReporteVentaProductoService{
    
    @EJB
    private IVentaProductoDAO ventaProductoDAO;

    @Override
    public List<VentaProductoData> get() {
        return ventaProductoDAO.getVentaPorProducto();
    }
    
}
