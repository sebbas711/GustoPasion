/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import pyp.excepciones.BusinessException;
import pyp.excepciones.ExceptionType;


public class MessageUtil {

    public static void sendMessage(String clientID, String message, String detail, FacesMessage.Severity severity, Boolean prop){
        FacesMessage fm = new FacesMessage(severity, message, detail);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(clientID, fm);
        fc.getExternalContext().getFlash().setKeepMessages(prop);
    }

    public static void sendBusinessException(String clientID, BusinessException businessException){
        sendMessage(clientID, businessException.getMessage(), businessException.getDetails(), getSevetiry(businessException.getType()), Boolean.TRUE);
    }
    
    private static FacesMessage.Severity getSevetiry(ExceptionType exceptionType){
        switch (exceptionType) {
            case ERROR:
                return FacesMessage.SEVERITY_ERROR;
            case INFO:
                return FacesMessage.SEVERITY_INFO;
            case WARNING:
                return FacesMessage.SEVERITY_WARN;
            default:
                return FacesMessage.SEVERITY_FATAL;
        }
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
    
    public static void sendInfoModal(String title, String detail){
        PrimeFaces.current().executeScript(buildMessageModal(title, detail, "success"));
    }
    
    public static void sendSuccessModal(String title, String detail){
        PrimeFaces.current().executeScript(buildMessageModal(title, detail, "success"));
    }
    
    public static void sendErrorModal(String title, String detail){
        PrimeFaces.current().executeScript(buildMessageModal(title, detail, "error"));
    }
    
    private static String buildMessageModal(String title, String detail, String type){
        return "swal('"
                .concat(title)
                .concat("','")
                .concat(detail)
                .concat("','")
                .concat(type)
                .concat("');");
    }

}
