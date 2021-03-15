/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import pyp.DAO.IRolDAO;
import pyp.DAO.IUsuarioDAO;
import pyp.modelo.entidades.Rol;
import pyp.modelo.entidades.Usuario;
import pyp.servicios.usuarios.IRegistroUsuarioService;
import pyp.util.Email;
import pyp.util.MessageUtil;

/**
 *
 * @author PC
 */
@Named(value = "usuarioControlador")
@ViewScoped
public class UsuarioControlador implements Serializable {

    @EJB
    private IUsuarioDAO usuarioDAO;
    @EJB
    private IRegistroUsuarioService registroUsuarioService;
    private List<Usuario> usuarios;
    private Usuario UsuarioSeleccionado;
    private Usuario nuevoUsuario;
    private String correo = "";

    public UsuarioControlador() {
    }

    @PostConstruct
    public void init() {
        nuevoUsuario = new Usuario();
        nuevoUsuario.setRoles(new ArrayList<>());
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
            if (nuevoUsuario.getId() != null
                    && nuevoUsuario.getPrimerNombre() != null
                    && nuevoUsuario.getPrimerApellido() != null
                    && nuevoUsuario.getEmail() != null
                    && nuevoUsuario.getDireccion() != null
                    && nuevoUsuario.getContraseña() != null) {
                if (Objects.isNull(nuevoUsuario.getRoles()) || nuevoUsuario.getRoles().isEmpty()) {
                    registroUsuarioService.registrarCliente(nuevoUsuario);
                } else {
                    registroUsuarioService.registrarUsuario(nuevoUsuario);
                }
                mensajeRequest = "swal('Registro Exitoso', '', 'success');";
                MessageUtil.sendInfo(null, "Registro Exitoso",
                        "Listado en la tabla Usuarios", Boolean.FALSE);
            } else {
                MessageUtil.sendError(null, "Los campos son obligatorios",
                        "Por favor diligencie todos los campos", Boolean.FALSE);
                mensajeRequest = "swal('Los campos son obligatorios', 'Por favor diligencie todos los campos', 'info');";
            }

        } catch (Exception e) {
            System.out.println("pyp.modelo.controlador.UsuarioControlador.registrar()" + e.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
            MessageUtil.sendError(null, "Verifique sus datos",
                    "Intente de nuevo", Boolean.FALSE);
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoUsuario = new Usuario();

    }

    public void recuperarClave() {
        String mensajeRequest = "";
        Usuario usuarioResultado = new Usuario();
        try {
            usuarioResultado = usuarioDAO.recuperarClave(correo);
            int claveNew = (int) (Math.random() * 100000);
            usuarioResultado.setContraseña("GP-" + claveNew);
            usuarioDAO.edit(usuarioResultado);
            Email.sendClaves(usuarioResultado.getEmail(), usuarioResultado.getPrimerNombre() + " "
                    + usuarioResultado.getPrimerApellido(), correo, "GP-" + claveNew);
            MessageUtil.sendInfo(null, "Contraseña enviado con exito al correo",
                    "Revise su Correo", Boolean.FALSE);
        } catch (Exception e) {
            MessageUtil.sendError(null, "Datos Incorrectos, revise sus Datos",
                    "e Intente Nuevamente", Boolean.FALSE);
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

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Usuario newU = new Usuario();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (j) {
                        case 0:
                            newU.setId((int) hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 1:
                            newU.setTipoIdentificación(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newU.setPrimerNombre(hssfCell.toString());
                            filasContador++;
                            break;

                        case 3:
                            newU.setSegundoNombre(hssfCell.toString());
                            filasContador++;
                            break;
                        case 4:
                            newU.setPrimerApellido(hssfCell.toString());
                            filasContador++;
                            break;
                        case 5:
                            newU.setSegundoApellido(hssfCell.toString());
                            filasContador++;
                            break;
                        case 6:
                            newU.setDireccion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 7:
                            newU.setEmail(hssfCell.toString());
                            filasContador++;
                            break;
                        case 8:
                            newU.setTelefono((int) hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 9:
                            newU.setContraseña(hssfCell.toString());
                            filasContador++;
                            break;
                        case 10:
                            newU.setEstado((int) hssfCell.getNumericCellValue());
                            filasContador++;
                            break;

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargaListaUsuarios(FileUploadEvent event) throws IOException {
        InputStream input = event.getFile().getInputStream();
        List cellData = new ArrayList();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp = new ArrayList();
                while (iterator.hasNext()) {
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            }
            insertarXLS(cellData);
        } catch (Exception e) {
            System.out.println("hola" + e.getMessage());
            PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("Usuario.xhtml");
    }

    public void descargaListado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("UsuarioReporte", "Juan Sebastian Luna");
            parametro.put("RutaImagen", context.getRealPath("/resource/img/Report.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/basededatos", "root", "");

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListaUsuarios.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ListadoUsuarios.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }
    
        public void descargaCertificado(String idUsuario) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("id_usuario", idUsuario);
            parametro.put("RutaImagen", context.getRealPath("/resource/imagenes/Report.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "");

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ReporteUsuario.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=CertificadoIndividual.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
