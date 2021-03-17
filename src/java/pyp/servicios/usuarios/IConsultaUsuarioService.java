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
public interface IConsultaUsuarioService {
    
    Usuario consultarPorId(Integer idUsuario) throws BusinessException;
    
}
