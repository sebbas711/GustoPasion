/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.reportes;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.dto.VentaProductoData;

/**
 *
 * @author PC
 */
@Local
public interface IReporteVentaProductoService {
    
    List<VentaProductoData> get();
    
}
