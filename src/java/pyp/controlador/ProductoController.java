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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author PC
 */
@Named
@SessionScoped
public class ProductoController implements Serializable{
    
    @EJB
    private IProductoDAO productoDAO;
    private List<Producto> productos;
    private Producto productoSeleccionado;
    
    
    private Producto producto;
    private List<InsumosDelProducto> insumoDelProducto;
    private Insumo insumoSeleccionado;
    private Integer cantidad;

    public ProductoController() {
    }
    
    @PostConstruct
    public void init(){
        insumoDelProducto = new ArrayList<>();
        
        producto = new Producto();
        producto.setInsumosDelProducto(insumoDelProducto);
    }
    
    public void agregarInsumo(InsumosDelProducto insumoProducto){
         InsumosDelProducto nuevoInsumoProducto = new InsumosDelProducto();
         nuevoInsumoProducto.setInsumo(insumoSeleccionado);
         nuevoInsumoProducto.setCantidadInsumo(cantidad);
         insumoDelProducto.add(insumoProducto);
    }

    public Producto getProducto() {
        return producto;
    }

    public List<InsumosDelProducto> getInsumoDelProducto() {
        return insumoDelProducto;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }
    
    public void seleccionarProducto(Producto p){
        System.out.println("Se ha seleccionado el usuario");
        System.out.println("p");
        this.productoSeleccionado = p;
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListaProductos.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ListadoProductos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }
    
        public void descargaCantidadProducto() {
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/CantidadProductoVendidoPorMes.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ReporteCantidadProductos.pdf");
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
