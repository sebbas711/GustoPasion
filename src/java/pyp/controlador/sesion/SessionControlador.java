package pyp.controlador.sesion;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Permiso;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.IPermissionService;
import pyp.servicios.ISessionService;
import pyp.util.MessageUtil;
import pyp.util.RedirectUtil;

/**
 *
 * @GAES 1
 */
@Named(value = "sessionControlador")
@SessionScoped
public class SessionControlador implements Serializable {

    private static final String URL_INDEX = "/app/index.xhtml";

    @EJB
    private ISessionService sessionService;
    @EJB
    private IPermissionService permissionService;

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
                rolSeleccionado = user.getRoles().get(0);
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
                ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
            } else if (sessionService.isAdmin(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            } else if (sessionService.isCashier(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            }else if (sessionService.isAuxCo(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            }else if (sessionService.isOperario(user)) {
                ec.redirect(ec.getRequestContextPath() + "/app/index.xhtml");
            }else {
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
                break;
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
            ec.redirect(ec.getRequestContextPath() + "/app/Usuario/InicioSesion.xhtml");
        }
    }

    public void cerrarSesion() {
        user = null;
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ext.getRequestContextPath();

        try {
            ((HttpSession) ext.getSession(false)).invalidate();
            ext.redirect(ctxPath + "/index.xhtml");
        } catch (Exception e) {
            System.out.println("Error UsuarioSesion:cerraSesion" + e.getMessage());
        }
    }

    public boolean isAdmin() {
        return sessionService.isAdmin(user);
    }

    public boolean isCashier() {
        return sessionService.isCashier(user);
    }

    public boolean isAuxCo() {
        return sessionService.isAuxCo(user);
    }

    public boolean isOperario() {
        return sessionService.isOperario(user);
    }

    public boolean isCustomer() {
        return sessionService.isCustomer(user);
    }

    public void validarPermisos() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        String urlRecursoSolicitado = ((HttpServletRequest) ec.getRequest()).getRequestURI();
        if (!permissionService.hasPermission(rolSeleccionado, urlRecursoSolicitado)) {
            RedirectUtil.redirectTo(URL_INDEX);
        }
    }

    public List<Permiso> getPermisosSuperiores() {
        return permissionService.getTopPermissions(rolSeleccionado);
    }

    public List<Permiso> getSubPermisos(Permiso permisoSuperior) {
        return permissionService.getSubPermissions(rolSeleccionado, permisoSuperior);
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
