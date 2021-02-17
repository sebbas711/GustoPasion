/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author PC
 */
public class MessageUtil {

    public static void sendMessage(String clientID, String message, String detail, FacesMessage.Severity severity, Boolean prop){
        FacesMessage fm = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(clientID, fm);
    }

    public static void sendInfo(String clientID, String message, String detail, Boolean prop) {
        sendMessage(clientID, message, detail, FacesMessage.SEVERITY_INFO, prop);
    }

    public static void sendError(String clientID, String message, String detail, Boolean prop) {
        sendMessage(clientID, message, detail, FacesMessage.SEVERITY_ERROR, prop);
    }

    public static void sendMessageInfo(Object object, String usuario_Incativo, String debe_contactar_al_administrador_para_acti) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void sendMessageInfo(Object object, String usuario_Incativo, String debe_contactar_al_administrador_para_acti, Boolean FALSE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
