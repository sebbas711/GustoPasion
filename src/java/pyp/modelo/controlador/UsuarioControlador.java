/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import pyp.modelo.DAO.IUsuarioDAO;
import pyp.modelo.entidades.Usuario;
import pyp.modelo.util.Email;
import pyp.modelo.util.MessageUtil;

/**
 *
 * @author PC
 */
@Named(value = "usuarioControlador")
@ViewScoped
public class UsuarioControlador implements Serializable {

    @EJB
    private IUsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
    private Usuario UsuarioSeleccionado;
    private Usuario nuevoUsuario;

    private String correo = "";

    public UsuarioControlador() {
    }

    @PostConstruct
    public void init() {
        nuevoUsuario = new Usuario();
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = usuarioDAO.findAll();
        }
        return usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return UsuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario UsuarioSeleccionado) {
        this.UsuarioSeleccionado = UsuarioSeleccionado;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public void seleccionarUsuario(Usuario usuarios) {
        System.out.println("Se ha seleccionado un Usuario");
        System.out.println(usuarios);
        this.UsuarioSeleccionado = usuarios;

    }

    public void registrar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            nuevoUsuario.setEstado(Short.valueOf("1"));
            usuarioDAO.create(nuevoUsuario);
        } catch (Exception e) {
        }

    }

    public void recuperarClave() {
        String mensaje = "Usuario con el correo: " + correo;
        Usuario usuarioResultado = new Usuario();
        usuarioResultado = usuarioDAO.recuperarClave(correo);

        if (usuarioResultado.getPrimerNombre() == null) {
            mensaje += " NO ESTA EN LA BASE DE DATOS ";
        } else {
            try {
                int nuevaClave = (int) (Math.random() * 100000);
                usuarioResultado.setContraseña("RE-" + nuevaClave);
                usuarioDAO.edit(usuarioResultado);
                
                Email.sendClaves(usuarioResultado.getEmail(),
                        usuarioResultado.getPrimerNombre() + " " + usuarioResultado.getPrimerApellido(),
                        usuarioResultado.getEmail(),
                        "RE-"+nuevaClave);

            } catch (Exception e) {
                System.out.println("error enviando mensaje de recuperación -->" + e.getMessage());
            }
            mensaje += " su clave se envio al correo registrado.";
        }
        FacesMessage ms = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, ms);
    }

    public void eliminar() {
        try {
            usuarioDAO.remove(UsuarioSeleccionado);
            MessageUtil.sendInfo(null, "El usuario se ha eliminado correctamente", "", false);
            usuarios = null;
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.sendError(null, "Error al eliminar el usuario", e.getMessage(), false);
        }
    }

    public void actualizar() {
        try {
            if (UsuarioSeleccionado != null) {
                usuarioDAO.edit(UsuarioSeleccionado);
                MessageUtil.sendInfo(null, "La informacion del usuario se ha modificado correctamente", "", false);
                usuarios = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.sendError(null, "Error al modificar la infromacion del usuario", e.getMessage(), false);
        }

    }

    public void bloquearODesbloquear() {
        try {
            if (UsuarioSeleccionado != null) {
                if (UsuarioSeleccionado.getEstado() != 0) {
                    UsuarioSeleccionado.setEstado((short) 0);
                } else {
                    UsuarioSeleccionado.setEstado((short) 1);
                }
                usuarioDAO.edit(UsuarioSeleccionado);
                MessageUtil.sendInfo(null, "El estado del usuario se ha modificado correctamente", "", false);
                usuarios = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.sendError(null, "Error al modificar el estado del usuario", e.getMessage(), false);
        }
    }

    public boolean renderedBtnBloquear(Usuario usuario) {
        return (usuario.getEstado() != 0);
    }

    public String getBtnValueBloquear() {
        if (UsuarioSeleccionado != null) {
            if (UsuarioSeleccionado.getEstado() == 0) {
                return "Desbloquear";
            }
            return "Bloquear";
        }
        return "";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
