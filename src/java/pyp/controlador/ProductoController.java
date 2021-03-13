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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import pyp.DAO.ICategoriaProductoDAO;
import pyp.DAO.IProductoDAO;
import pyp.modelo.entidades.CategoriaProducto;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.InsumosDelProducto;
import pyp.modelo.entidades.Producto;

/**
 *
 * @author PC
 */
@Named
@SessionScoped
public class ProductoController implements Serializable {

    @EJB
    private IProductoDAO productoDAO;
    private List<Producto> productos;
    private Producto productoSeleccionado;

    private Producto producto;
    private List<InsumosDelProducto> insumoDelProducto;
    private Insumo insumoSeleccionado;
    private Integer cantidad;

    @EJB
    ICategoriaProductoDAO ICategoriaDAO;

    public ProductoController() {
    }

    @PostConstruct
    public void init() {
        insumoDelProducto = new ArrayList<>();

        producto = new Producto();
        producto.setInsumosDelProducto(insumoDelProducto);
    }

    public void agregarInsumo(InsumosDelProducto insumoProducto) {
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

    public void seleccionarProducto(Producto p) {
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

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Producto newP = new Producto();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (j) {
                        case 0:
                            CategoriaProducto nueva = ICategoriaDAO.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setCategoriaProducto(nueva);
                            filasContador++;
                            break;
                        case 1:
                            newP.setNombre(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setPrecio(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 3:
                            newP.setDescripcion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 4:
                            newP.setImagenP(hssfCell.toString());
                            filasContador++;
                            break;
                        case 5:
                            newP.setEstado((int) Math.floor(hssfCell.getNumericCellValue()));
                            filasContador++;
                            break;
                    }
                }
                productoDAO.create(newP);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargaListaProductos(FileUploadEvent event) throws IOException {
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
            PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("ControlProductos.xhtml");
    }

}
