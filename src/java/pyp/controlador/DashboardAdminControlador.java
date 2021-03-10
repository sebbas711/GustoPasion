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
import pyp.modelo.dto.VentaMesData;
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
    
    @PostConstruct
    public void init(){
        ventasMes = reporteVentaMesService.get();
    }

    public List<VentaMesData> getVentasMes() {
        return ventasMes;
    }
    
    public String getChartData(){
        Gson gson = new Gson();
        return gson.toJson(toChartPoints());
    }
    
    private List<ChartPoint> toChartPoints(){
        List<ChartPoint> chartPoints = new ArrayList<>();
        for(VentaMesData ventaMesData: ventasMes){
            chartPoints.add(new ChartPoint(ventaMesData.getTotalMes(), ventaMesData.getAnio() + "-" + ventaMesData.getMes()));
        }
        return chartPoints;
    }
    
}
