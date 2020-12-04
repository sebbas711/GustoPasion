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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import pyp.modelo.DAO.IUsuarioDAO;
import pyp.modelo.entidades.Usuario;
import pyp.modelo.util.Email;

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
        
        String mensajeRequest = "";
        try {
            nuevoUsuario.setEstado(Short.valueOf("1"));
            usuarioDAO.create(nuevoUsuario);
            mensajeRequest = "swal('Registro Exitoso', '', 'success');";
        } catch (Exception e) {
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoUsuario = new Usuario();

    }

    public void recuperarClave() {
        
        String mensajeRequest = "";
        Usuario usuarioResultado = new Usuario();
        
        try {
            usuarioResultado = usuarioDAO.recuperarClave(correo);
            int claveNew = (int) (Math.random()*100000);
            usuarioResultado.setContraseña("GP-"+claveNew);
            usuarioDAO.edit(usuarioResultado);
            mensajeRequest += "swal('Envio Exitoso', 'Clave enviada al correo registrado', 'success');";
            Email.sendClaves(usuarioResultado.getEmail(), usuarioResultado.getPrimerNombre() + " " + 
                    usuarioResultado.getPrimerApellido(), correo, "GP-"+claveNew);
        } catch (Exception e) {
            System.out.println("Error RegistroRequest:recuperarClave" + e.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        correo = "";
    }

    public void eliminar() {
        String mensajeRequest = "";
        try {
            usuarioDAO.remove(UsuarioSeleccionado);
            mensajeRequest = "swal('Usuario Eliminado', 'Correctamente', 'success');";
            usuarios = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar el usuario', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void actualizar() {
        String mensajeRequest = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (UsuarioSeleccionado != null) {
                usuarioDAO.edit(UsuarioSeleccionado);
                mensajeRequest = "swal('Actulizado', 'Correctamente', 'success');";
                usuarios = null;
            }
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo actualizar el usuario', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);

    }

    public void bloquearODesbloquear() {
        String mensajeRequest = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (UsuarioSeleccionado != null) {
                if (UsuarioSeleccionado.getEstado() != 0) {
                    UsuarioSeleccionado.setEstado((short) 0);
                } else {
                    UsuarioSeleccionado.setEstado((short) 1);
                }
                usuarioDAO.edit(UsuarioSeleccionado);
                mensajeRequest = "swal('Estado el Usuario', 'Modificado', 'success');";
                usuarios = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo cambiar el estado del usuario', 'error');";
        }
        
        PrimeFaces.current().executeScript(mensajeRequest);
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
