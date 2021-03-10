/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.pqrs;

import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Pqrs;

/**
 *
 * @author Gaes5
 */
@Local
public interface IRegistroPqrsService {
    
    void registroPqrs(Pqrs pqrs) throws BusinessException;
    
}
