package pyp.modelo.login;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import pyp.modelo.DAO.IUsuarioDAO;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.modelo.util.MessageUtil;

@Named(value = "sessionControlador")
@SessionScoped
public class SessionControlador implements Serializable {

    @EJB
    private IUsuarioDAO uDAO;
    private String email;
    private String password;
    private Usuario user;

    public SessionControlador() {
    }

    @PostConstruct
    public void init() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    /*public boolean isAdministrator(){
        for (Rol r : user.getRol()){
            if(r.getId() == 1){
                return true;
            }
        }
        return false;
    }*/
    /*public boolean isCajero(){
        for (Rol r : user.getRol()){
            if(r.getId() == 2){
                return true;
            }
        }
        return false;
    }*/
     /*public boolean isAuxCocina(){
        for (Rol r : user.getRol()){
            if(r.getId() == 3){
                return true;
            }
        }
        return false;
    }*/
     /*public boolean isOperario(){
        for (Rol r : user.getRol()){
            if(r.getId() == 4){
                return true;
            }
        }
        return false;
    }*/
    /*public boolean isClient(){
        for (Rol r : user.getRol()){
            if(r.getId() == 5){
                return true;
            }
        }
        return false;
    }*/
    
    public boolean isStartSession() {
        return user != null;
    }

    public void validarSesion() throws IOException {
        if (!isStartSession()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/app/Usuario/InicioSesion.xhtml");
        }
    }

    public void startSession() throws IOException {

        if (email != null && email.length() > 0
                && password != null && password.length() > 0) {
            user = uDAO.findByEmailAndPassword(email, password);
            if (user != null) {
                switch (user.getEstado()) {
                    case 0:
                        MessageUtil.sendInfo(null, "Usuario Incativo",
                                "Debe contactar al administrador para activar su usuario.", Boolean.FALSE);
                        user = null;
                        break;
                    case 1:
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        ec.redirect(ec.getRequestContextPath() + "/app/Inventario/RegistroInsumo.xhtml");
                        break;
                }
            } else {
                MessageUtil.sendInfo(null, "Datos Incorrectos",
                        "Verifique sus datos y vuelva a intentarlo", Boolean.FALSE);
            }
        } else {
            MessageUtil.sendInfo(null, "Datos Obligatorios", "Debe diligenciar todos los campos", Boolean.FALSE);
        }
    }

}
