/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Usuario;

/**
 *
 * @author Ismael
 */
@Local
public interface ISessionService {
    
    Usuario login(String email, String password) throws BusinessException;
    
    boolean isCustomer(Usuario user);
    
    boolean isAdmin(Usuario user);

    boolean isCashier(Usuario user);
    
}
