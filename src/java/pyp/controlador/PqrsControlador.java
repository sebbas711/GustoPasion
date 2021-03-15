/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

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
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.PrimeFaces;
import pyp.DAO.IPqrsDAO;
import pyp.controlador.sesion.SessionControlador;
import pyp.modelo.entidades.Pqrs;
import pyp.modelo.entidades.Respuestapqrs;
import pyp.servicios.pqrs.IRegistroPqrsService;

/**
 *
 * @author alejo
 */
@Named(value = "pqrsControlador")
@ViewScoped
public class PqrsControlador implements Serializable {

    @Inject
    private SessionControlador session;
    @EJB
    private IPqrsDAO pqDAO;
    @EJB
    private IRegistroPqrsService registroPqrsService;
    private List<Pqrs> pqrs;
    private Pqrs pqrsSeleccionada;
    private Pqrs nuevaPqrs;

    private Respuestapqrs respuestaSeleccionada;

    private boolean formRadicarEnable;
    private boolean tablaPqrsEnable;

    /**
     * Creates a new instance of PqrsControlador
     */
    public PqrsControlador() {
    }

    @PostConstruct
    public void init() {
        nuevaPqrs = new Pqrs();
        formRadicarEnable = false;
        tablaPqrsEnable = false;
    }

    public List<Pqrs> getPqrs() {
        if (pqrs == null || pqrs.isEmpty()) {
            pqrs = pqDAO.finByCustomer(session.getUser().getCliente());
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

    public Respuestapqrs getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(Respuestapqrs respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public Pqrs getNuevaPqrs() {
        return nuevaPqrs;
    }

    public void setNuevaPqrs(Pqrs nuevaPqrs) {
        this.nuevaPqrs = nuevaPqrs;
    }

    public boolean isFormRadicarEnable() {
        return formRadicarEnable;
    }

    public boolean isTablaPqrsEnable() {
        return tablaPqrsEnable;
    }

    public void seleccionarPqrs(Pqrs pq) {
        System.out.println("Se ha seleccionado una pqrs");
        System.out.println(pq);
        this.pqrsSeleccionada = pq;

    }

    public void seleccionarRespuestaPqrs(Respuestapqrs rp) {
        System.out.println("Se ha seleccionado una pqrs");
        System.out.println(rp);
        this.respuestaSeleccionada = rp;

    }

    public void registrarPqrs() {
        String mensajeRequest = "";
        try {
            compltarInfoPQRS();
            registroPqrsService.registroPqrs(nuevaPqrs);
            mensajeRequest = "swal('Registro Exitoso', '', 'success');";
        } catch (Exception e) {
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevaPqrs = new Pqrs();
    }

    private void compltarInfoPQRS() {
        nuevaPqrs.setCliente(session.getUser().getCliente());
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

    public void eliminar() {
        String mensajeRequest = "";
        try {
            pqDAO.remove(pqrsSeleccionada);
            mensajeRequest = "swal('PQRS Eliminada', 'Correctamente', 'success');";
            pqrs = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar la PQRS', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    /*public void cambiarEstado() {
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
    }*/
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListadoPqrs.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ListadoPQRS.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }

    public void descargaCantidadPqrsPorMes() {
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/CantidadPqrsPorMes.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-Reporte_CantidadPqrsPorMes.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }

    public void showFromRadicar() {
        tablaPqrsEnable = false;
        formRadicarEnable = true;
    }

    public void showTablaPqrs() {
        formRadicarEnable = false;
        tablaPqrsEnable = true;
    }
}
