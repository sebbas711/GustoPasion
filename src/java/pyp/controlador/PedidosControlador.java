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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pyp.DAO.IPedidoDAO;
import pyp.excepciones.BusinessException;
import pyp.modelo.entidades.Pedido;
import pyp.servicios.IPedidosService;
import pyp.util.MessageUtil;

/**
 *
 * @GAES 1
 */
@Named(value = "pedidosControlador")
@ViewScoped
public class PedidosControlador implements Serializable {

    @EJB
    private IPedidoDAO pedidosDAO;
    private List<Pedido> pedidos;
    private Pedido pedidoSeleccionado;

    /*@EJB
     private IPedidosService estadoPqrsService;*/
    public PedidosControlador() {
    }

    @PostConstruct
    public void init() {
        pedidos = null;
    }

    public List<Pedido> getPedidos() {
        if (pedidos == null || pedidos.isEmpty()) {
            pedidos = pedidosDAO.findAll();
        }
        return pedidos;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public void seleccionarPedido(Pedido pedidos) {
        System.out.println("Se ha seleccionado un Pedido");
        System.out.println(pedidos);
        this.pedidoSeleccionado = pedidos;

    }

    public void actualizarDatosPedido() {
        String mensajeRequest = "";
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (pedidoSeleccionado != null) {
                pedidosDAO.edit(pedidoSeleccionado);
                mensajeRequest = "swal('Actulizado', 'Correctamente', 'success');";
                pedidos = null;
            }
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo actualizar el pedido', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);

    }

    /*public void cambiarEstadoFinalizada() throws BusinessException {
        estadoPqrsService.cambiarestadoPedido(pedidoSeleccionado);
    }*/
    public void eliminar() {
        String mensajeRequest = "";
        try {
            pedidosDAO.remove(pedidoSeleccionado);
            mensajeRequest = "swal('Pedido Eliminado', 'Correctamente', 'success');";
            pedidos = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar el pedido', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListadoPedidos.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ListaPedidos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }

    public void descargaListadoVenta() {
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/VentaPorMesReporte.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-VentaPorMes.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }

}
