/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.dto;

/**
 *
 * @author PC
 */
public class ChartPointProducto {
        private int y;
    private String label;

    public ChartPointProducto() {
    }

    public ChartPointProducto(int y, String label) {
        this.y = y;
        this.label = label;
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
    
}
