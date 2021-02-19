/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.TipoInsumo;

/**
 *
 * @GAES 5
 */
@Local
public interface IInsumoDAO extends DAO<Insumo> {

    List<Insumo> findByTipoInsumo(TipoInsumo tipoInsumoFiltro);
    
}
