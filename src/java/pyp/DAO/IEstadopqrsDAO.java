/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.DAO;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Estadopqrs;

/**
 *
 * @GAES 1
 */
@Local
public interface IEstadopqrsDAO extends DAO<Estadopqrs> {
    
    static final Integer ID_ESTADO_NUEVA = 1;
    static final Integer ID_ESTADO_FINALIZADA = 3;

    Estadopqrs findEstadoNueva();

    Estadopqrs findEstadoFinalizada();
    
}
