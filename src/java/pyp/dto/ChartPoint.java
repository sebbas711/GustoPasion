/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.dto;

/**
 *
 * @GAES 1
 */
public class ChartPoint {
    
    private double y;
    private String label;

    public ChartPoint() {
    }

    public ChartPoint(double y, String label) {
        this.y = y;
        this.label = label;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
