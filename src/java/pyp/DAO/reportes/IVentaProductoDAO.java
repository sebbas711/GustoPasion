/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO.reportes;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.dto.VentaProductoData;

/**
 *
 * @GAES 1
 */
@Local
public interface IVentaProductoDAO {
    
    List<VentaProductoData> getVentaPorProducto();
    
}
