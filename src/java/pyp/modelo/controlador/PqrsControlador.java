/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.modelo.controlador;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;
import pyp.modelo.DAO.IPqrsDAO;
import pyp.modelo.entidades.Pqrs;

/**
 *
 * @author alejo
 */
@Named(value = "pqrsControlador")
@ViewScoped
public class PqrsControlador implements Serializable {

    @EJB

    private IPqrsDAO pqDAO;
    private List<Pqrs> pqrs;
    private Pqrs pqrsSeleccionada;
    private Pqrs nuevaPqrs;

    /**
     * Creates a new instance of PqrsControlador
     */
    public PqrsControlador() {
    }

    @PostConstruct
    public void init() {
        nuevaPqrs = new Pqrs();
    }

    public List<Pqrs> getPqrs() {
        if (pqrs == null || pqrs.isEmpty()) {
            pqrs = pqDAO.findAll();
        }
        return pqrs;
    }

    public void setPqrs(List<Pqrs> pqrs) {
        this.pqrs = pqrs;
    }

    public Pqrs getPqrsSeleccionada() {
        return pqrsSeleccionada;
    }

    public void setPqrsSeleccionada(Pqrs pqrsSeleccionada) {
        this.pqrsSeleccionada = pqrsSeleccionada;
    }

    public Pqrs getNuevaPqrs() {
        return nuevaPqrs;
    }

    public void setNuevaPqrs(Pqrs nuevaPqrs) {
        this.nuevaPqrs = nuevaPqrs;
    }

    public void seleccionarPqrs(Pqrs pqrs) {
        System.out.println("Se ha seleccionado un Usuario");
        System.out.println(pqrs);
        this.pqrsSeleccionada = pqrs;

    }

    public void registrarPqrs() {
        String mensajeRequest = "";
        try {
            nuevaPqrs.setId(pqDAO.count() + 1);
            nuevaPqrs.setFecha(new Date());
            nuevaPqrs.setEstado(Short.valueOf("0"));
            pqDAO.create(nuevaPqrs);
            mensajeRequest = "swal('Registro Exitoso', '', 'success');";
        } catch (Exception e) {
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevaPqrs = new Pqrs();
    }

    public void actualizar() {

        String mensajeRequest = "";
        try {
            if (pqrsSeleccionada != null) {
                pqDAO.edit(pqrsSeleccionada);
                mensajeRequest = "swal('Respuesta Realizada', 'No olvide Cambiar el estado', 'success');";
                pqrs = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo responder la PQRS', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void cambiarEstado() {
        String mensajeRequest = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (pqrsSeleccionada != null) {
                if (pqrsSeleccionada.getEstado() != 0) {
                    pqrsSeleccionada.setEstado((short) 0);
                } else {
                    pqrsSeleccionada.setEstado((short) 1);
                }
                pqDAO.edit(pqrsSeleccionada);
                mensajeRequest = "swal('Estado de la PQRS', 'Modificado', 'success');";
                pqrs = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo cambiar el estado de la PQRS', 'error');";
        }

        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public boolean renderedBtnEstado(Pqrs pqrs) {
        return (pqrs.getEstado() != 0);
    }

    public String getBtnValueEstado() {
        if (pqrsSeleccionada != null) {
            if (pqrsSeleccionada.getEstado() == 0) {
                return "Responder";
            }
            return "En espera";
        }
        return "";
    }

    public void descargaListado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("UsuarioReporte", "Alejandro Parias");
            parametro.put("RutaImagen", context.getRealPath("/resource/imagenes/Report.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "");

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListaPqrs.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Certificado.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
        }
    }
    
     public void descargaCertificado(String idPQRS) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("idPQRS", idPQRS);
            parametro.put("RutaImagen", context.getRealPath("/resource/imagenes/Report.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "");

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ReportePQRS.jasper"));

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
}
