/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.dto.ChartPoint;
import pyp.dto.ChartPointProducto;
import pyp.modelo.dto.VentaMesData;
import pyp.modelo.dto.VentaProductoData;
import pyp.servicios.reportes.IReporteVentaProductoService;

/**
 *
 * @author PC
 */
@Named
@ViewScoped
public class DashboardProductoControlador implements Serializable {
    
    @EJB
    private IReporteVentaProductoService reporteVentaProductoService;
    
    private List<VentaProductoData> ventaProducto;
    
    @PostConstruct
    public void init(){
        ventaProducto = reporteVentaProductoService.get();
    }

    public List<VentaProductoData> getVentaProducto() {
        return ventaProducto;
    }
    
     public String getChartData(){
        Gson gson = new Gson();
        return gson.toJson(toChartPoints());
    }
    
    private List<ChartPointProducto> toChartPoints(){
        List<ChartPointProducto> chartPoints = new ArrayList<>();
        for(VentaProductoData ventaProductoData: ventaProducto){
            chartPoints.add(new ChartPointProducto(ventaProductoData.getCantidadProductoVendida(), ventaProductoData.getNombreCategoria()));
        }
        return chartPoints;
    }
    
}
