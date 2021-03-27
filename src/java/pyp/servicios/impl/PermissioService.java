/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import pyp.modelo.entidades.Permiso;
import pyp.modelo.entidades.Rol;
import pyp.servicios.IPermissionService;
/**
 *
 * @GAES 1
 */

@Stateless
public class PermissioService implements IPermissionService {

    @Override
    public boolean hasPermission(Rol rol, String urlToValidate) {
        for (Permiso permiso : rol.getPermisos()) {
            if (Objects.nonNull(permiso.getUrl()) && urlToValidate.endsWith(permiso.getUrl())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Permiso> getTopPermissions(Rol rol) {
        List<Permiso> permisosSuperiores = new ArrayList<>();
        for (Permiso permiso : rol.getPermisos()) {
            if (permiso.getPermisoSuperior() == null) {
                permisosSuperiores.add(permiso);
            }
        }
        return permisosSuperiores;
    }
    
    @Override
    public List<Permiso> getSubPermissions(Rol rol, Permiso topPermission){
        List<Permiso> subPermisos = new ArrayList<>();
        for (Permiso permission : rol.getPermisos()) {
            if (Objects.nonNull(permission.getPermisoSuperior()) && Objects.nonNull(topPermission)
                    && Objects.equals(permission.getPermisoSuperior().getId(), topPermission.getId())) {
                subPermisos.add(permission);
            }
        }
        return subPermisos;
    }
    
}
