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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import pyp.DAO.IAuxCocinaDAO;
import pyp.DAO.IInsumoDAO;
import pyp.DAO.ITipoInsumoDAO;
import pyp.modelo.entidades.AuxCocina;
import pyp.modelo.entidades.Insumo;
import pyp.modelo.entidades.TipoInsumo;
import pyp.util.MessageUtil;

/**
 *
 * @author PC
 */
@Named(value = "inventarioControlador")
@ViewScoped
public class InventarioControlador implements Serializable {

    @EJB

    private IInsumoDAO IDAO;
    private List<Insumo> insumos;
    private Insumo insumoSeleccionado;
    private Insumo nuevoInsumo;

    @EJB
    private ITipoInsumoDAO ITipoDAO;

    @EJB
    private IAuxCocinaDAO AuxDAO;

    public InventarioControlador() {
    }

    @PostConstruct
    public void init() {
        nuevoInsumo = new Insumo();

    }

    public List<Insumo> getInsumos() {
        if (insumos == null || insumos.isEmpty()) {
            insumos = IDAO.findAll();
        }
        return insumos;
    }

    public Insumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setInsumoSeleccionado(Insumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public Insumo getNuevoInsumo() {
        return nuevoInsumo;
    }

    public void setNuevoInsumo(Insumo nuevoInsumo) {
        this.nuevoInsumo = nuevoInsumo;
    }

    public void seleccionarInsumo(Insumo i) {
        System.out.println("Se ha seleccionado el insumo");
        System.out.println(i);
        this.insumoSeleccionado = i;
    }

    public void registrar() {
        String mensajeRequest = "";
        try {
            Date fechaactual = new Date();
            if (nuevoInsumo.getFechaVencimiento().after(fechaactual) && nuevoInsumo.getTipoInsumo() != null
                    && nuevoInsumo.getAuxCocina() != null && nuevoInsumo.getNombre() != null
                    && nuevoInsumo.getDescripcion() != null) {
                nuevoInsumo.setFechaIngreso(new Date());
                nuevoInsumo.setEstado(Short.valueOf("1"));
                IDAO.create(nuevoInsumo);
                mensajeRequest = "swal('Registro Exitoso', '', 'success');";
                MessageUtil.sendInfo(null, "Registro Exitoso",
                        "Listado en Control de Insumos", Boolean.FALSE);
            } else {
                MessageUtil.sendInfo(null, "Fecha de Vencimiento debe ser superior a la fecha de registro",
                        "Por favor diligencie todos los campos", Boolean.FALSE);
            }
        } catch (Exception ex) {
            System.out.println("Error UsuarioControlador:registrar " + ex.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoInsumo = new Insumo();
    }

    public void registrarmodal() {
        String mensajeRequest = "";
        try {
            Date fechaactual = new Date();
            if (nuevoInsumo.getFechaVencimiento().after(fechaactual) && nuevoInsumo.getTipoInsumo() != null
                    && nuevoInsumo.getAuxCocina() != null && nuevoInsumo.getNombre() != null
                    && nuevoInsumo.getDescripcion() != null) {
                nuevoInsumo.setFechaIngreso(new Date());
                nuevoInsumo.setEstado(Short.valueOf("1"));
                insumos.clear();
                insumos.addAll(IDAO.findAll());
                IDAO.create(nuevoInsumo);
                mensajeRequest = "swal('Registro Exitoso', '', 'success');";
                MessageUtil.sendInfo(null, "Registro Exitoso",
                        "Listado en Control de Insumos", Boolean.FALSE);
            } else {
                MessageUtil.sendInfo(null, "Fecha de Vencimiento debe ser superior a la fecha de registro",
                        "Por favor diligencie todos los campos", Boolean.FALSE);
            }
        } catch (Exception ex) {
            System.out.println("Error UsuarioControlador:registrar " + ex.getMessage());
            mensajeRequest = "swal('Verifique sus datos', 'Intente de nuevo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
        nuevoInsumo = new Insumo();
    }

    public void eliminarInsumo() {
        String mensajeRequest = "";
        try {
            IDAO.remove(insumoSeleccionado);
            mensajeRequest = "swal('Insumo Eliminado', 'Correctamente', 'success');";
            insumos = null;
        } catch (Exception e) {
            mensajeRequest = "swal('Error', 'No se pudo eliminar el Insumo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void actualizar() {

        String mensajeRequest = "";
        try {
            if (insumoSeleccionado != null) {
                IDAO.edit(insumoSeleccionado);
                mensajeRequest = "swal('Actualizado', 'Correctamente', 'success');";
                insumos = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo modificar la informacion del insumo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public void bloquearODesbloquear() {
        String mensajeRequest = "";
        try {
            if (insumoSeleccionado != null) {
                if (insumoSeleccionado.getEstado() != 0) {
                    insumoSeleccionado.setEstado((short) 0);
                } else {
                    insumoSeleccionado.setEstado((short) 1);
                }
                IDAO.edit(insumoSeleccionado);
                mensajeRequest = "swal('Estado del Insumo', 'Modificado', 'success');";
                insumos = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensajeRequest = "swal('Error', 'No se pudo cambiar el estado del Insumo', 'error');";
        }
        PrimeFaces.current().executeScript(mensajeRequest);
    }

    public boolean renderedBtnBloquear(Insumo insumo) {
        return (insumo.getEstado() != 0);
    }

    public String getBtnValueBloquear() {
        if (insumoSeleccionado != null) {
            if (insumoSeleccionado.getEstado() == 0) {
                return "Desbloquear";
            }
            return "Bloquear";
        }
        return "";
    }

    private Date Date(int par, int par1, int par2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Date after(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ListaInsumos.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Reporte-ListadoInsumos.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (Exception e) {
            System.out.println("pyp.modelo.reportes.insumos.ListaInsumos()" + e.getMessage());
        }
    }

    public void descargaCertificado(String idInsumos) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("tipoInsumo", idInsumos);
            parametro.put("RutaImagen", context.getRealPath("/resource/imagenes/Report.jpg"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "");

            File jasper = new File(context.getRealPath("/WEB-INF/classes/pyp/modelo/reportes/ReporteInsumos.jasper"));

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

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Insumo newI = new Insumo();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (j) {
                        case 0:
                            TipoInsumo nueva = ITipoDAO.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newI.setTipoInsumo(nueva);
                            filasContador++;
                            break;
                        case 1:
                            AuxCocina nuevo = AuxDAO.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newI.setAuxCocina(nuevo);
                            filasContador++;
                            break;
                        case 2:
                            newI.setFechaIngreso(hssfCell.getDateCellValue());
                            filasContador++;
                            break;

                        case 3:
                            newI.setFechaVencimiento(hssfCell.getDateCellValue());
                            filasContador++;
                            break;
                        case 4:
                            newI.setNombre(hssfCell.toString());
                            filasContador++;
                            break;
                        case 5:
                            newI.setDescripcion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 6:
                            newI.setCantidad(hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                        case 7:
                            newI.setEstado((int) hssfCell.getNumericCellValue());
                            filasContador++;
                            break;
                    }
                }
                IDAO.create(newI);
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
        context.redirect("control_inventario.xhtml");
    }

    
}
