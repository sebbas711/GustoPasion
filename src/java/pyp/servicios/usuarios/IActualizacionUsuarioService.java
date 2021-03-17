/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.usuarios;

import javax.ejb.Local;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Usuario;

@Local
public interface IActualizacionUsuarioService {
    
    void actualizaDatosBasicos(Usuario usuario) throws BusinessException;
    
    void actualizaClave(Integer idUsuario, String claveAnterior, String claveNueva) throws BusinessException;
    
}
