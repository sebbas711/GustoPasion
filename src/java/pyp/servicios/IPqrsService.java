/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import java.util.List;
import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Estadopqrs;
import pyp.modelo.entidades.Pqrs;

@Local
public interface IPqrsService {
    
    List<Estadopqrs> getEstadoPqrsHabilitados() throws BusinessException;

    public List<Pqrs> findPqrsByFilter(Estadopqrs estadoPqrsFiltro) throws BusinessException;
    
}
