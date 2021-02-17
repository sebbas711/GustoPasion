package pyp.controlador.sesion;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.ISessionService;
import pyp.util.MessageUtil;
import pyp.util.RedirectUtil;

@Named(value = "sessionControlador")
@SessionScoped
public class SessionControlador implements Serializable {

    @EJB
    private ISessionService sessionService;

    private String email;
    private String password;
    private Usuario user;
    private Rol rolSeleccionado;

    public SessionControlador() {
    }

    @PostConstruct
    public void init() {
    }

    public String startSession() throws IOException {
        if (datosDeLoginValidos()) {
            try {
                user = sessionService.login(email, password);
                redirectUser();
            } catch (BusinessException ex) {
                showMessageStarSession(ex);
            }
        } else {
            MessageUtil.sendInfo(null, "Datos Obligatorios", "Debe diligenciar todos los campos", Boolean.FALSE);
        }
        return "";
    }

    private boolean datosDeLoginValidos() {
        return email != null && email.trim().length() > 0
                && password != null && password.trim().length() > 0;
    }

    private void redirectUser() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            if (sessionService.isCustomer(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/ServicioAlCliente/Pagina.xhtml");
            } else if (sessionService.isAdmin(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            } else {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMessageStarSession(BusinessException ex) {
        switch (ex.getMessageException()) {
            case BE_USUARIO_INACTIVO:
                MessageUtil.sendError(null, ex.getMessage(), ex.getDetails(), Boolean.FALSE);
                break;
            case BE_USUARIO_NO_EXISTE:
                MessageUtil.sendInfo(null, ex.getMessage(), ex.getDetails(), Boolean.FALSE);
            default:
                MessageUtil.sendInfo(null, ex.getMessage(), ex.getDetails(), Boolean.FALSE);
        }
    }

    public boolean isStartSession() {
        return user != null;
    }

    public void validarSesion() throws IOException {
        if (!isStartSession()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            String val1 = ec.getRequestPathInfo();
            String val2 = ((HttpServletRequest)ec.getRequest()).getRequestURI();
            ec.redirect(ec.getRequestContextPath() + "/app/Usuario/InicioSesion.xhtml");
        }
    }

    public void cerrarSesion() {
        user = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        RedirectUtil.redirectTo("/index.xhtml");
    }
    
    public boolean isAdmin(){
        return sessionService.isAdmin(user);
    }
    
    public boolean isCashier(){
        return sessionService.isCashier(user);
    }
    
    public void validaPaginasAdmin(){
        if(!isAdmin()){
            RedirectUtil.redirectTo("/app/index.xhtml");
        }
    }
    public void validaPaginasCashier(){
        if(!isCashier()){
            RedirectUtil.redirectTo("/app/index.xhtml");
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    
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
    
    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }
    
    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
    //</editor-fold>
}
