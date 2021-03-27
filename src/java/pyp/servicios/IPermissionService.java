/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios;

import java.util.List;
import javax.ejb.Local;
import pyp.modelo.entidades.Permiso;
import pyp.modelo.entidades.Rol;

/**
 *
 * @GAES 1
 */
@Local
public interface IPermissionService {
    
    boolean hasPermission(Rol rol, String urlToValidate);
    
    List<Permiso> getTopPermissions(Rol rol);
    
    List<Permiso> getSubPermissions(Rol rol, Permiso topPermission);
    
}
