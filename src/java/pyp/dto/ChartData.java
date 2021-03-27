/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.dto;

import java.util.List;

/**
 *
 * @GAES 1
 */
public class ChartData {

    private String type = "column";
    private boolean showInLegend = true;
    private String legendMarkerColor;
    private String legendText;
    List<Object> dataPoints;

    private int y;
    private String label;
    
    public ChartData(String legendText, List<Object> dataPoints) {
        this.legendText = legendText;
        this.dataPoints = dataPoints;
    }
    

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isShowInLegend() {
        return showInLegend;
    }

    public void setShowInLegend(boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    public String getLegendMarkerColor() {
        return legendMarkerColor;
    }

    public void setLegendMarkerColor(String legendMarkerColor) {
        this.legendMarkerColor = legendMarkerColor;
    }

    public String getLegendText() {
        return legendText;
    }

    public void setLegendText(String legendText) {
        this.legendText = legendText;
    }

    public List<Object> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Object> dataPoints) {
        this.dataPoints = dataPoints;
    }

}
