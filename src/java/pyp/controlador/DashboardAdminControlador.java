/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.controlador;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import pyp.dto.ChartData;
import pyp.dto.ChartPoint;
import pyp.dto.ChartPoint1;
import pyp.modelo.dto.CantidadInsumo;
import pyp.modelo.dto.PorcentajePQRS;
//import pyp.dto.ChartPoint1;
//import pyp.modelo.dto.PorcentajePQRS;
import pyp.modelo.dto.VentaMesData;
import pyp.servicios.reportes.IReportePqrsMes;
//import pyp.servicios.reportes.IReportePqrsMes;
import pyp.servicios.reportes.IReporteVentaMesService;

/**
 *
 * @author Gaes5
 */
@Named
@ViewScoped
public class DashboardAdminControlador implements Serializable {
    
    @EJB
    private IReporteVentaMesService reporteVentaMesService;
    
    private List<VentaMesData> ventasMes;
    
    @EJB
    private IReportePqrsMes reportePqrsMes;
    
    private List<PorcentajePQRS> pqrsMes;
    
    private List<CantidadInsumo> cantidadInsumo;
    
    
    
    @PostConstruct
    public void init(){
        ventasMes = reporteVentaMesService.get();
        pqrsMes = reportePqrsMes.get();
        cantidadInsumo = new ArrayList<>();
    }

    public List<VentaMesData> getVentasMes() {
        return ventasMes;
    }

    public List<PorcentajePQRS> getPqrsMes() {
        return pqrsMes;
    }
    
    public String getChartData(){
        Gson gson = new Gson();
        return gson.toJson(toChartPoints());
    }
    
    public String getChartData1(){
        Gson gson1 = new Gson();
        return gson1.toJson(toChartPoints1());
    }
    
    private List<ChartPoint> toChartPoints(){
        List<ChartPoint> chartPoints = new ArrayList<>();
        for(VentaMesData ventaMesData: ventasMes){
            chartPoints.add(new ChartPoint(ventaMesData.getTotalMes(), ventaMesData.getAnio() + "-" + ventaMesData.getMes()));
        }
        return chartPoints;
    }
    
    private List<ChartPoint1> toChartPoints1(){
        List<ChartPoint1> chartPoints1 = new ArrayList<>();
        for(PorcentajePQRS porcentajePQRS: pqrsMes){
            chartPoints1.add(new ChartPoint1(porcentajePQRS.getTotalMes(), porcentajePQRS.getAnio() + "-" + porcentajePQRS.getMes()));
        }
        return chartPoints1;
    }
    
    public List<ChartData> getDataInsumo(){
        /*Map<String, List<CantidadInsumo>> group = cantidadInsumo.stream()
                .collect(Collectors.groupingBy(CantidadInsumo::getMes));
        List<ChartData> data = new ArrayList<>();
        return group.entrySet().stream()
                .map( e->new ChartData(
                        e.getKey(), 
                        e.getValue().stream()
                                .map(DashboardAdminControlador::toChartPoint1)
                ))
                .collect(Collectors.toList()) ;*/
        Map<String, ChartData> data = new HashMap<>();
        for(CantidadInsumo ci:cantidadInsumo){
            if(!data.containsKey(ci.getMes())){
                ChartData chart = new ChartData(ci.getMes(), new ArrayList<>());
                chart.getDataPoints().add(toChartPoint1(ci));
                data.put(ci.getMes(), chart);
            }else{
                data.get(ci.getMes()).getDataPoints().add(toChartPoint1(ci));
            }
        }
        return data.values().stream().collect(Collectors.toList());
    }
    
    private ChartPoint1 toChartPoint1(CantidadInsumo cantidadInsumo){
        return new ChartPoint1(cantidadInsumo.getCantidadInsumo(), cantidadInsumo.getNombreInsumo());
    }
    
    
}
