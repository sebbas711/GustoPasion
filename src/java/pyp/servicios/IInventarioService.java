/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import java.util.List;
import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.TipoInsumo;

@Local
public interface IInventarioService {
    
    List<TipoInsumo> getTiposInsumosHabilitados() throws BusinessException;

    public List<Insumo> findInsumosByFilter(TipoInsumo tipoInsumoFiltro) throws BusinessException;
    
}
