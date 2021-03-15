/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import pyp.DAO.IUsuarioDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.file.IFileSaveService;
import pyp.util.Email;
import pyp.util.MessageUtil;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class EnvioPromoControlador implements Serializable {

    @EJB(beanName = "ImagePromo")
    private IFileSaveService fileSaveService;
    @EJB
    private IUsuarioDAO usuarioDAO;
    private Part imgPromocion;
    private String asunto = "";

    public void correoMasivo() {
        /*
        String infoFile = imgPromocion.getSubmittedFileName()
                .concat(" - ")
                .concat(imgPromocion.getName())
                .concat(" - ")
                .concat(imgPromocion.getContentType())
                .concat(" - ")
                .concat(String.valueOf(imgPromocion.getSize()));
        */
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String pathImage = fileSaveService.save(imgPromocion, ec.getRealPath("/"));
            //http://localhost:8080/GustoPasion
            String urlImage = buildUrl(ec).concat("/").concat(pathImage);
            for (Usuario IUsuario : usuarioDAO.findAll()) {
                Email.sendBienvenido(IUsuario.getEmail(), 
                        "Señor(a) " + IUsuario.getPrimerNombre() + " " + IUsuario.getPrimerApellido(), 
                        asunto, urlImage);
            }
            MessageUtil.sendInfoModal("Se ha publicitado", "Se ha enviado la publicidad de la promoción a los usuarios del sistema.");
        } catch (BusinessException ex) {
            ex.printStackTrace();
            MessageUtil.sendErrorModal(ex.getMessage(), ex.getDetails());
        } catch (Exception e) {
            MessageUtil.sendInfo(null, "Error",
                    "", Boolean.FALSE);
        }
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Part getImgPromocion() {
        return imgPromocion;
    }

    public void setImgPromocion(Part imgPromocion) {
        this.imgPromocion = imgPromocion;
    }
    
    private String buildUrl(ExternalContext ec){
        try {
            return new URL(ec.getRequestScheme(),
                    ec.getRequestServerName(),
                    ec.getRequestServerPort(),
                    ec.getRequestContextPath()).toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(EnvioPromoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

}
