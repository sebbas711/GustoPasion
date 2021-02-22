/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


public class RedirectUtil {

    private RedirectUtil() {

    }

    public static void redirectTo(String pathToView) {
        try {
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            String ctxPath = ext.getRequestContextPath();
            ext.redirect(ctxPath + pathToView);
        } catch (Exception e) {
            System.out.println("Error Redireccionando" + e.getMessage());
        }
    }

}
