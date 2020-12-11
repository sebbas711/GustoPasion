package pyp.modelo.login;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
    private Rol rolSeleccionado;

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

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }
    
    public String startSession() throws IOException {

        if (email != null && email.trim().length() > 0
                && password != null && password.trim().length() > 0) {
            user = uDAO.findByEmailAndPassword(email, password);
            if (user != null) {
                /*if(!user.getRol().isEmpty()){
                    rolSeleccionado = user.getRol().getId(1);
                    return "/app/Inventario/control_inventario.xhtml?faces-redirect=true";
                }else{
                    user = null;
                    MessageUtil.sendInfo(null, "Usuario sin Rol",
                                "el Usuario debe esperar a que se le asigne un Rol.", Boolean.FALSE);
                }*/
                switch (user.getEstado()) {
                    case 0:
                        MessageUtil.sendInfo(null, "Usuario Inactivo",
                                "Debe contactar al administrador para activar su usuario.", Boolean.FALSE);
                        user = null;
                        break;
                    case 1:
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        ec.redirect(ec.getRequestContextPath() + "/app/Inventario/control_inventario.xhtml");
                        break;
                }
            } else {
                MessageUtil.sendInfo(null, "Datos Incorrectos",
                        "Verifique sus datos y vuelva a intentarlo", Boolean.FALSE);
            }
        } else {
            MessageUtil.sendInfo(null, "Datos Obligatorios", "Debe diligenciar todos los campos", Boolean.FALSE);
        }
        return "";
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

}
